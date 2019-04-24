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

import com.androidcourse.manisha.instapost.Fragments.SelectedUserFragment;
import com.androidcourse.manisha.instapost.Model.User;
import com.androidcourse.manisha.instapost.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context mContext;
    private List<User> mUsers;

    public UserAdapter(Context mContext, List<User> mUsers) {
        this.mContext = mContext;
        this.mUsers = mUsers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final User currentUser = mUsers.get(i);
        viewHolder.userName.setText(currentUser.getName());
        viewHolder.nickName.setText(currentUser.getNickName());
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserDetails(currentUser);
            }
        });
    }

    private void getUserDetails(User user) {
        Bundle clickedUserDetails = new Bundle();
        clickedUserDetails.putString("email", user.getEmail());
        SelectedUserFragment selectedUserPosts = new SelectedUserFragment();
        selectedUserPosts.setArguments(clickedUserDetails);
        FragmentManager fragmentManager = ((FragmentActivity) mContext).getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, selectedUserPosts);
        transaction.commit();
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView userName;
        public TextView nickName;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = (TextView) itemView.findViewById(R.id.username);
            nickName = (TextView) itemView.findViewById(R.id.nickname);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }
}
