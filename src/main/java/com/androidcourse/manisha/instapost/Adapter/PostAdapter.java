package com.androidcourse.manisha.instapost.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidcourse.manisha.instapost.Model.Post;
import com.androidcourse.manisha.instapost.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Post> mPosts;

    public PostAdapter(Context mContext,List<Post> mPosts){
        this.mContext = mContext;
        this.mPosts = mPosts;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View v = LayoutInflater.from(mContext).inflate(R.layout.post_item,viewGroup,false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int i) {
        Post currentPost = mPosts.get(i);
        imageViewHolder.textViewNickName.setText(currentPost.getUser().getNickName());
        imageViewHolder.textViewDescription.setText(currentPost.getDescription());
        Picasso.with(mContext)
                .load(currentPost.getUrl())
                .fit()
                .centerCrop()
                .into(imageViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewDescription;
        public TextView textViewNickName;
        public ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNickName = itemView.findViewById(R.id.nickname_uploaded);
            textViewDescription = itemView.findViewById(R.id.description_uploaded);
            imageView = itemView.findViewById(R.id.post_uploaded);
        }
    }
}
