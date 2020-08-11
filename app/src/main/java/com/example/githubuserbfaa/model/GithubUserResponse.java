package com.example.githubuserbfaa.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GithubUserResponse {
    @SerializedName("total_count")
    private int total;
    @SerializedName("incomplete_results")
    private boolean status;
    @SerializedName("items")
    private List<GithubUser> itemGithubUser;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<GithubUser> getItemGithubUser() {
        return itemGithubUser;
    }

    public void setItemGithubUser(List<GithubUser> itemGithubUser) {
        this.itemGithubUser = itemGithubUser;
    }
}
