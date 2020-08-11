package com.example.githubuserbfaa.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.githubuserbfaa.model.GithubUser;
import com.example.githubuserbfaa.model.GithubUserResponse;
import com.example.githubuserbfaa.network.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private MutableLiveData<ArrayList<GithubUser>> githubUserMutableLiveData = new MutableLiveData<>();
    private ArrayList<GithubUser> githubUser = new ArrayList<>();

    public void setGithubList(String s) {
        Call<GithubUserResponse> responseCall = ApiClient.getData().searchUser(s);
        responseCall.enqueue(new Callback<GithubUserResponse>() {
            @Override
            public void onResponse(Call<GithubUserResponse> call, Response<GithubUserResponse> response) {
                githubUser = (ArrayList<GithubUser>) response.body().getItemGithubUser();
                githubUserMutableLiveData.setValue(githubUser);
            }

            @Override
            public void onFailure(Call<GithubUserResponse> call, Throwable t) {
                Log.d("Failure : ", String.valueOf(t.getMessage()));
            }
        });
    }

    public LiveData<ArrayList<GithubUser>> getUsers() {
        return githubUserMutableLiveData;
    }
}
