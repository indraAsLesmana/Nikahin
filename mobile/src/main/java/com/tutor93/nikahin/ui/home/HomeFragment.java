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
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tutor93.core.data.DataManager;
import com.tutor93.core.data.model.Invitation;
import com.tutor93.core.ui.home.HomeContract;
import com.tutor93.core.ui.home.HomePresenter;
import com.tutor93.nikahin.R;
import com.tutor93.nikahin.util.widgets.DetailFrameWrapper;

import java.util.List;


/**
 * Created by indraaguslesmana on 6/8/17.
 */

public class HomeFragment extends Fragment implements HomeContract.HomeClickView,
        OnMapReadyCallback, HomeGalleryListAdapter.OnImageClickListener {

    private static final String ARG_INVITATION = "argCharacter";

    private HomePresenter mHomePresenter;
    private Invitation mInvitation;
    private ImageView mHeaderImage;
    private LinearLayout mContentFrame;

    private DetailFrameWrapper mDetailWrapper;

    private boolean mapReady;
    private GoogleMap m_map;
    private MarkerOptions markPosition;
    private LatLng mLocation;
    private static final int ANIMATE_TIME = 10000; //10 seconds

    private AppCompatActivity mActivity;

    public static HomeFragment newInstance(Invitation invitation) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_INVITATION, invitation);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mHomePresenter = new HomePresenter(DataManager.getInstance());
        if (savedInstanceState != null) {
            mInvitation = savedInstanceState.getParcelable(ARG_INVITATION);
        } else if (getArguments() != null) {
            mInvitation = getArguments().getParcelable(ARG_INVITATION);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment)
                this.getChildFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);

        mHeaderImage = (ImageView) view.findViewById(R.id.iv_header);
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

        Glide.with(this)
                .load(mInvitation.invitationImage)
                .asBitmap()
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<Bitmap>(800, 480) {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                        BitmapDrawable bitmap1 = new
                                BitmapDrawable(getResources(), bitmap);
                        mHeaderImage.setBackground(bitmap1);
                    }
                });

        mContentFrame = (LinearLayout) view.findViewById(R.id.details_content_frame);
        if (mInvitation != null) {
            mDetailWrapper = new DetailFrameWrapper(mActivity, mInvitation, this);
            mContentFrame.addView(mDetailWrapper);
        }

        if (mInvitation.locations.latitude != null && mInvitation.locations.longitude != null){
            Double longitude = Double.parseDouble(mInvitation.locations.longitude);
            Double latitude = Double.parseDouble(mInvitation.locations.latitude);
            mLocation = new LatLng(latitude, longitude);
        }
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady = true;
        m_map = googleMap;

        if (m_map == null) return;

        markPosition = new MarkerOptions()
                .position(mLocation)
                .title(mInvitation.locations.name)
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.ic_location));

        m_map.addMarker(markPosition);
        moveCamera(markPosition.getPosition(), true);
    }

    private void moveCamera(LatLng latLng, boolean isAnimate) {
        CameraPosition target;

        if (isAnimate) {
            target = CameraPosition.builder()
                    .target(latLng)
                    .bearing(112)
                    .tilt(65)
                    .zoom(17)
                    .build();

            m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target), ANIMATE_TIME, null);
        } else {
            // just jump in right into. without animate
            target = CameraPosition.builder()
                    .target(latLng)
                    .zoom(17)
                    .build();
            m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
        }
    }

    @Override
    public void showImageList(List<String> imageList) {
        mDetailWrapper.loadImages(imageList);
    }
}
