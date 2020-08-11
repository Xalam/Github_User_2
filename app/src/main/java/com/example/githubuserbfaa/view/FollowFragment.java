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
import com.example.githubuserbfaa.adapter.FollowAdapter;
import com.example.githubuserbfaa.model.FollowModel;
import com.example.githubuserbfaa.model.GithubUser;
import com.example.githubuserbfaa.viewmodel.DetailViewModel;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class FollowFragment extends Fragment {
    private FollowAdapter followAdapter;
    private RecyclerView recyclerView;
    private LinearLayout linearLayout;
    private String username;

    public FollowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_follow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayout = view.findViewById(R.id.lin_not_found_follow);
        linearLayout.setVisibility(View.GONE);

        DetailActivity detailActivity = (DetailActivity) getActivity();
        GithubUser githubUser = detailActivity.getIntent().getParcelableExtra(DetailActivity.EXTRA_GITHUB);
        username = githubUser.getUserName();

        recyclerView = view.findViewById(R.id.rv_follow);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DetailViewModel detailViewModel = new ViewModelProvider(Objects.requireNonNull(getActivity()), new ViewModelProvider.NewInstanceFactory()).get(DetailViewModel.class);

        detailViewModel.setFollowUsers(username);
        detailViewModel.getFollowUsers().observe(getActivity(), new Observer<ArrayList<FollowModel>>() {
            @Override
            public void onChanged(ArrayList<FollowModel> followModels) {
                followAdapter = new FollowAdapter(followModels);
                recyclerView.setAdapter(followAdapter);
                if (followModels.size() == 0) {
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
