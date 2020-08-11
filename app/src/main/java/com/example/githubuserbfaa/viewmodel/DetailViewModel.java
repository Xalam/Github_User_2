package com.example.githubuserbfaa.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.githubuserbfaa.model.FollowModel;
import com.example.githubuserbfaa.model.FollowingModel;
import com.example.githubuserbfaa.network.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailViewModel extends ViewModel {
    private MutableLiveData<ArrayList<FollowModel>> followMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<FollowingModel>> followingMutableLiveData = new MutableLiveData<>();
    private ArrayList<FollowModel> followUsers = new ArrayList<>();
    private ArrayList<FollowingModel> followingUsers = new ArrayList<>();

    public LiveData<ArrayList<FollowModel>> getFollowUsers() {
        return followMutableLiveData;
    }

    public void setFollowUsers(final String username) {
        Call<ArrayList<FollowModel>> responseCall = ApiClient.getData().followersUser(username);
        responseCall.enqueue(new Callback<ArrayList<FollowModel>>() {
            @Override
            public void onResponse(Call<ArrayList<FollowModel>> call, Response<ArrayList<FollowModel>> response) {
                if (response.isSuccessful()) {
                    followUsers = response.body();
                    followMutableLiveData.setValue(followUsers);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<FollowModel>> call, Throwable t) {
                Log.d("Failure : ", String.valueOf(t.getMessage()));
            }
        });
    }

    public LiveData<ArrayList<FollowingModel>> getFollowingUsers() {
        return followingMutableLiveData;
    }

    public void setFollowingUsers(final String username) {
        Call<ArrayList<FollowingModel>> responseCall = ApiClient.getData().followingUser(username);
        responseCall.enqueue(new Callback<ArrayList<FollowingModel>>() {
            @Override
            public void onResponse(Call<ArrayList<FollowingModel>> call, Response<ArrayList<FollowingModel>> response) {
                if (response.isSuccessful()) {
                    followingUsers = response.body();
                    followingMutableLiveData.setValue(followingUsers);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<FollowingModel>> call, Throwable t) {
                Log.d("Failure : ", String.valueOf(t.getMessage()));
            }
        });
    }
}
