package com.example.githubuserbfaa.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubuserbfaa.DetailActivity;
import com.example.githubuserbfaa.R;
import com.example.githubuserbfaa.adapter.FollowingAdapter;
import com.example.githubuserbfaa.model.FollowingModel;
import com.example.githubuserbfaa.model.GithubUser;
import com.example.githubuserbfaa.viewmodel.DetailViewModel;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class FollowingFragment extends Fragment {
    private FollowingAdapter followingAdapter;
    private LinearLayout linearLayout;
    private RecyclerView recyclerView;
    private String username;

    public FollowingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayout = view.findViewById(R.id.lin_not_found_following);
        linearLayout.setVisibility(View.GONE);

        DetailActivity detailActivity = (DetailActivity) getActivity();
        GithubUser githubUser = detailActivity.getIntent().getParcelableExtra(DetailActivity.EXTRA_GITHUB);
        username = githubUser.getUserName();

        recyclerView = view.findViewById(R.id.rv_following);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DetailViewModel detailViewModel = new ViewModelProvider(Objects.requireNonNull(getActivity()), new ViewModelProvider.NewInstanceFactory()).get(DetailViewModel.class);
        detailViewModel.setFollowingUsers(username);
        detailViewModel.getFollowingUsers().observe(getActivity(), new Observer<ArrayList<FollowingModel>>() {
            @Override
            public void onChanged(ArrayList<FollowingModel> followingModels) {
                followingAdapter = new FollowingAdapter(followingModels);
                recyclerView.setAdapter(followingAdapter);
                if (followingModels.size() == 0) {
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
