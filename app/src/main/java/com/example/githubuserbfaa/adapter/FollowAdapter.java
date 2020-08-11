package com.example.githubuserbfaa.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.githubuserbfaa.R;
import com.example.githubuserbfaa.model.FollowModel;

import java.util.ArrayList;

public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.ListViewHolder> {

    private ArrayList<FollowModel> listFollowModel = new ArrayList<>();

    public FollowAdapter(ArrayList<FollowModel> items) {
        this.listFollowModel = items;
    }

    @NonNull
    @Override
    public FollowAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_follow, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowAdapter.ListViewHolder holder, int position) {
        final FollowModel followModel = listFollowModel.get(position);
        Glide.with(holder.itemView.getContext())
                .load(followModel.getAvatarFollow())
                .apply(new RequestOptions().override(60, 60))
                .into(holder.avatarFollow);
        holder.tvUsername.setText(followModel.getUserName());
        holder.tvHtmlUrl.setText(followModel.getHtmlUrl());
    }

    @Override
    public int getItemCount() {
        return listFollowModel.size();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView avatarFollow;
        TextView tvUsername, tvHtmlUrl;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);

            avatarFollow = itemView.findViewById(R.id.ci_avatar_follow);
            tvUsername = itemView.findViewById(R.id.tv_username_follow);
            tvHtmlUrl = itemView.findViewById(R.id.tv_htmlurl_follow);
        }
    }
}
