package com.tutor93.nikahin.ui.home;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.tutor93.core.data.DataManager;
import com.tutor93.core.data.model.History;
import com.tutor93.core.ui.home.HomeContract;
import com.tutor93.core.ui.home.HomePresenter;
import com.tutor93.nikahin.R;
import com.tutor93.nikahin.util.Constant;

import java.util.List;

/**
 * Created by indraaguslesmana on 6/8/17.
 */

public class HomeFragment extends Fragment implements HomeContract.HomeClickView{

    private HomePresenter mHomePresenter;
    private List<History.Invitation> mHistory;
    private ImageView mHeaderImage;

    private AppCompatActivity mActivity;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomePresenter = new HomePresenter(DataManager.getInstance());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mHeaderImage = (ImageView) view.findViewById(R.id.iv_header);

        mHomePresenter.attachView(this);
        initViews(view);
        mHomePresenter.onHistoryRequest(Constant.TOKEN); // trigger to load API

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

      /*  Glide.with(this)
                .load(mHistory.get(0).imageGallery.get(0))
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                        BitmapDrawable bitmap1 = new
                                BitmapDrawable(getResources(), bitmap);
                        mHeaderImage.setBackground(bitmap1);
                    }
                });*/

    }


    @Override
    public void showHistoryList(List<History.Invitation> histories) {
        mHistory = histories;
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
