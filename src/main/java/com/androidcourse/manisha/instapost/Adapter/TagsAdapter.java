package com.androidcourse.manisha.instapost.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidcourse.manisha.instapost.Fragments.SelectedHashFragment;
import com.androidcourse.manisha.instapost.R;

import java.util.List;

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.ViewHolder>{
    private Context mContext;
    private List<String> mHashTags;

    public TagsAdapter(Context mContext, List<String> mHashTags) {
        this.mContext = mContext;
        this.mHashTags = mHashTags;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.hash_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final String currentHash = mHashTags.get(i);
        viewHolder.hashTag.setText("#"+currentHash);
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, currentHash, Toast.LENGTH_SHORT).show();
                getHashDetails(currentHash);
            }
        });
    }

    private void getHashDetails(String hashTag) {
        Bundle clickedHashDetails = new Bundle();
        clickedHashDetails.putString("hashTag", hashTag);
        SelectedHashFragment selectedHashPosts = new SelectedHashFragment();
        selectedHashPosts.setArguments(clickedHashDetails);
        FragmentManager fragmentManager = ((FragmentActivity) mContext).getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, selectedHashPosts);
        transaction.addToBackStack("hash_posts");
        transaction.commit();
    }
    @Override
    public int getItemCount() {
        return mHashTags.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView hashTag;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hashTag = (TextView) itemView.findViewById(R.id.hashtag);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }
}
