package com.rapidbizapps.tdd.ui.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.rapidbizapps.tdd.data.model.User;

import java.util.List;

/**
 * Created by mlanka on 5/10/16.
 */

class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.CustomViewHolder> {
    Context mContext;
    List<User> mUsers;

    UsersAdapter(Context context, List<User> users) {
        mContext = context;
        mUsers = users;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        public CustomViewHolder(View itemView) {
            super(itemView);
        }
    }
}
