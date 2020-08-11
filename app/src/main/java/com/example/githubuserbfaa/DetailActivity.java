package com.example.githubuserbfaa;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.githubuserbfaa.adapter.PagerAdapter;
import com.example.githubuserbfaa.model.GithubUser;
import com.example.githubuserbfaa.network.ApiClient;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_GITHUB = "extra_github";
    private ProgressBar progressBar;
    private TextView tvFullname, tvUsername, tvHtmlUrl, tvLocation, tvCompany, tvFollowers, tvFollowing, tvRepository;
    private String userName;
    private ImageView avatarGithub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        progressBar = findViewById(R.id.progressBar_detail);
        tvFullname = findViewById(R.id.tv_detail_fullname);
        tvUsername = findViewById(R.id.tv_detail_username);
        tvHtmlUrl = findViewById(R.id.tv_detail_url);
        tvLocation = findViewById(R.id.tv_detail_location);
        tvCompany = findViewById(R.id.tv_detail_company);
        tvFollowers = findViewById(R.id.tv_detail_followers);
        tvFollowing = findViewById(R.id.tv_detail_following);
        tvRepository = findViewById(R.id.tv_detail_repository);
        avatarGithub = findViewById(R.id.ci_avatar_detail);

        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_chevron);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        showProgressBar(true);

        GithubUser githubUser = getIntent().getParcelableExtra(EXTRA_GITHUB);
        if (githubUser != null) {
            userName = githubUser.getUserName();
            tvUsername.setText(userName);
        }

        setDetailUser(userName);

        PagerAdapter pagerAdapter = new PagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.vp_detail);
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tl_detail);
        tabLayout.setupWithViewPager(viewPager);

        Window window = DetailActivity.this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(DetailActivity.this, R.color.colorBlue));
        }
    }

    private void showProgressBar(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void setDetailUser(String username) {
        Call<GithubUser> responseCall = ApiClient.getData().detailUser(username);
        responseCall.enqueue(new Callback<GithubUser>() {
            @Override
            public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
                if (response.body() != null) {
                    showProgressBar(false);
                    if (response.body().getFullName() == null) tvFullname.setText("-");
                    else tvFullname.setText(response.body().getFullName());

                    tvHtmlUrl.setText(response.body().getHtmlUrl());

                    if (response.body().getLocation() == null) tvLocation.setText("-");
                    else tvLocation.setText(response.body().getLocation());

                    if (response.body().getCompany() == null) tvCompany.setText("-");
                    else tvCompany.setText(response.body().getCompany());

                    tvFollowers.setText(String.valueOf(response.body().getFollowers()));
                    tvFollowing.setText(String.valueOf(response.body().getFollowing()));
                    tvRepository.setText(String.valueOf(response.body().getRepository()));

                    Glide.with(getApplicationContext())
                            .load(response.body().getAvatar())
                            .apply(new RequestOptions().override(100, 100))
                            .into(avatarGithub);
                }
            }

            @Override
            public void onFailure(Call<GithubUser> call, Throwable t) {
                Log.d("Failure : ", String.valueOf(t.getMessage()));
                Toast.makeText(DetailActivity.this, "User not found", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
