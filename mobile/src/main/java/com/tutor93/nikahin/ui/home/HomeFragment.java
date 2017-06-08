package com.tutor93.nikahin.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tutor93.core.ui.home.HomeContract;
import com.tutor93.core.ui.home.HomePresenter;
import com.tutor93.nikahin.R;

/**
 * Created by indraaguslesmana on 6/8/17.
 */

public class HomeFragment extends Fragment implements HomeContract.HomeClickView{

    private HomePresenter mHomePresenter;
    private AppCompatActivity mActivity;



    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomePresenter = new HomePresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mHomePresenter.attachView(this);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        mActivity = (AppCompatActivity) getActivity();
        mActivity.setSupportActionBar(toolbar);
        ActionBar actionBar = mActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        /*Picasso.with(mActivity)
                .l
                .centerCrop()
                .fit()
                .into((ImageView) view.findViewById(R.id.iv_header));*/
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showUnauthorizedError() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void showMessageLayout(boolean show) {

    }
}
