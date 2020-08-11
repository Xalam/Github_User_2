package com.example.githubuserbfaa.network;

import com.example.githubuserbfaa.model.FollowModel;
import com.example.githubuserbfaa.model.FollowingModel;
import com.example.githubuserbfaa.model.GithubUser;
import com.example.githubuserbfaa.model.GithubUserResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("search/users")
    @Headers("Authorization: token YOUR_TOKEN")
    Call<GithubUserResponse> searchUser(@Query("q") String username);

    @GET("users/{username}")
    @Headers("Authorization: token YOUR_TOKEN")
    Call<GithubUser> detailUser(@Path("username") String username);

    @GET("users/{username}/followers")
    @Headers("Authorization: token YOUR_TOKEN")
    Call<ArrayList<FollowModel>> followersUser(@Path("username") String username);

    @GET("users/{username}/following")
    @Headers("Authorization: token YOUR_TOKEN")
    Call<ArrayList<FollowingModel>> followingUser(@Path("username") String username);
}
