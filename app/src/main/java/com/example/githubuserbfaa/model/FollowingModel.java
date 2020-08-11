package com.example.githubuserbfaa.model;

import com.google.gson.annotations.SerializedName;

public class FollowingModel {
    @SerializedName("login")
    private String userName;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("avatar_url")
    private String avatarFollow;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getAvatarFollow() {
        return avatarFollow;
    }

    public void setAvatarFollow(String avatarFollow) {
        this.avatarFollow = avatarFollow;
    }
}
