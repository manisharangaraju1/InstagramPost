package com.androidcourse.manisha.instapost.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidcourse.manisha.instapost.Adapter.TagsAdapter;
import com.androidcourse.manisha.instapost.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HashTagsFragment extends Fragment {
    private View searchTagsView;
    private RecyclerView mRecyclerView;
    private TagsAdapter mAdapter;

    private DatabaseReference mDatabaseRef;
    private List<String> mHashTags;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView = (RecyclerView) searchTagsView.findViewById(R.id.searchTags_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("HashTags");
        mHashTags = new ArrayList<>();
        mAdapter = new TagsAdapter(getActivity(),mHashTags);
        mRecyclerView.setAdapter(mAdapter);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        searchTagsView = inflater.inflate(R.layout.fragment_hash_tags, container, false);
        return searchTagsView;
    }
    @Override
    public void onStart() {
        super.onStart();

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                        String tag = String.valueOf(postSnapshot.getValue());
                        mHashTags.add(tag);
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
