package com.tutor93.nikahin.util.widgets;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.tutor93.core.data.model.Invitation;
import com.tutor93.nikahin.R;
import com.tutor93.nikahin.ui.home.HomeGalleryListAdapter;

import java.util.List;

/**
 * Created by indraaguslesmana on 6/15/17.
 */

public class DetailFrameWrapper extends LinearLayout{

    private HomeGalleryListAdapter mHomeGalleryListAdapter;

    public DetailFrameWrapper(Context context, Invitation invitation,
                              HomeGalleryListAdapter.OnImageClickListener listener) {
        super(context);
        mHomeGalleryListAdapter = new HomeGalleryListAdapter(invitation.imageGallery, listener);
        init(context);

    }

    private void init(Context context) {
        inflate(context, R.layout.content_invitation, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_contentimage);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setMotionEventSplittingEnabled(false);
        recyclerView.setAdapter(mHomeGalleryListAdapter);

    }

    public void loadImages(List<String> imageList) {
        for (int i = 0; i < imageList.size(); i++) {
            mHomeGalleryListAdapter.getItem(i);
            mHomeGalleryListAdapter.notifyItemChanged(i);
        }
    }
}
