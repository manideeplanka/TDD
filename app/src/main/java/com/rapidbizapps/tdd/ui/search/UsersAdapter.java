package com.rapidbizapps.tdd.ui.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.rapidbizapps.tdd.R;
import com.rapidbizapps.tdd.data.model.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mlanka on 5/10/16.
 */

class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.CustomViewHolder> {
    Context mContext;
    List<User> mUsers;

    private static final String TAG = "UsersAdapter";

    UsersAdapter(Context context, List<User> users) {
        mContext = context;
        mUsers = users;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, parent, false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        User user = mUsers.get(position);
        holder.username_tv.setText(user.getName());
        holder.userEmail_tv.setText(user.getEmail());
        holder.userAvatar_iv.setImageURI(user.getAvatarUrl());
        Log.d(TAG, "onBindViewHolder: " + user.getName());
        Log.d(TAG, "onBindViewHolder: avatar url " + user.getAvatarUrl());
        Log.d(TAG, "onBindViewHolder: email " + user.getEmail());
    }

    void setItems(List<User> users) {
        mUsers = users;
        notifyDataSetChanged();

        Log.d(TAG, "setItems: ");
    }

    @Override
    public int getItemCount() {
        if (mUsers != null)
            return mUsers.size();

        return 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.user_name)
        TextView username_tv;

        @BindView(R.id.user_email)
        TextView userEmail_tv;

        @BindView(R.id.user_avatar)
        SimpleDraweeView userAvatar_iv;

        public CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
