package com.example.githubuserbfaa.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.githubuserbfaa.DetailActivity;
import com.example.githubuserbfaa.R;
import com.example.githubuserbfaa.model.GithubUser;

import java.util.ArrayList;

public class GithubUserAdapter extends RecyclerView.Adapter<GithubUserAdapter.ListViewHolder> {

    private ArrayList<GithubUser> listGithubUser;

    public GithubUserAdapter(ArrayList<GithubUser> items) {
        this.listGithubUser = items;
    }

    @NonNull
    @Override
    public GithubUserAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GithubUserAdapter.ListViewHolder holder, int position) {
        final GithubUser githubUser = listGithubUser.get(position);
        Glide.with(holder.itemView.getContext())
                .load(githubUser.getAvatar())
                .apply(new RequestOptions().override(60, 60))
                .into(holder.avatarGithub);
        holder.tvUsername.setText(githubUser.getUserName());
        holder.tvHtmlUrl.setText(githubUser.getHtmlUrl());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_GITHUB, githubUser);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listGithubUser.size();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView avatarGithub;
        TextView tvUsername, tvHtmlUrl;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);

            avatarGithub = itemView.findViewById(R.id.ci_avatar);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvHtmlUrl = itemView.findViewById(R.id.tv_htmlurl);
        }
    }
}
