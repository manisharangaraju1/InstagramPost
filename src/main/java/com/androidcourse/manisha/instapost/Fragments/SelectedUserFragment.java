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

import com.androidcourse.manisha.instapost.Adapter.PostAdapter;
import com.androidcourse.manisha.instapost.Model.Post;
import com.androidcourse.manisha.instapost.Model.User;
import com.androidcourse.manisha.instapost.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class SelectedUserFragment extends Fragment {
    private View selectedUserView;
    private RecyclerView mRecyclerView;
    private PostAdapter mAdapter;

    private DatabaseReference mDatabaseRef;
    private List<Post> mPosts;

    String userEmail="";

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView = (RecyclerView) selectedUserView.findViewById(R.id.selectedPostsDisplay);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Posts");
        mPosts = new ArrayList<>();
        mAdapter = new PostAdapter(getContext(),mPosts);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        selectedUserView = inflater.inflate(R.layout.fragment_selected_user, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            userEmail = bundle.getString("email","");
        }
        return selectedUserView;
    }

    @Override
    public void onStart() {
        super.onStart();

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                        Post post = postSnapshot.getValue(Post.class);
                        User user = post.getUser();
                        if(userEmail.equals(user.getEmail())){
                            mPosts.add(post);
                        }
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
