package com.example.githubuserbfaa.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.githubuserbfaa.R;
import com.example.githubuserbfaa.view.FollowFragment;
import com.example.githubuserbfaa.view.FollowingFragment;

public class PagerAdapter extends FragmentPagerAdapter {
    @StringRes
    private final int[] tab_titles = new int[]{
            R.string.followers,
            R.string.following
    };
    private Context mContext;

    public PagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FollowFragment();
                break;
            case 1:
                fragment = new FollowingFragment();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(tab_titles[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
