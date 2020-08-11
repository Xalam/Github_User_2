package com.example.githubuserbfaa.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class GithubUser implements Parcelable {
    public static final Creator<GithubUser> CREATOR = new Creator<GithubUser>() {
        @Override
        public GithubUser createFromParcel(Parcel in) {
            return new GithubUser(in);
        }

        @Override
        public GithubUser[] newArray(int size) {
            return new GithubUser[size];
        }
    };
    @SerializedName("login")
    private String userName;
    @SerializedName("name")
    private String fullName;
    @SerializedName("location")
    private String location;
    @SerializedName("company")
    private String company;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("public_repos")
    private int repository;
    @SerializedName("followers")
    private int followers;
    @SerializedName("following")
    private int following;
    @SerializedName("avatar_url")
    private String avatar;

    protected GithubUser(Parcel in) {
        userName = in.readString();
        fullName = in.readString();
        location = in.readString();
        company = in.readString();
        htmlUrl = in.readString();
        repository = in.readInt();
        followers = in.readInt();
        following = in.readInt();
        avatar = in.readString();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public int getRepository() {
        return repository;
    }

    public void setRepository(int repository) {
        this.repository = repository;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userName);
        parcel.writeString(fullName);
        parcel.writeString(location);
        parcel.writeString(company);
        parcel.writeString(htmlUrl);
        parcel.writeInt(repository);
        parcel.writeInt(followers);
        parcel.writeInt(following);
        parcel.writeString(avatar);
    }
}
