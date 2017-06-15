package com.tutor93.nikahin.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tutor93.core.data.model.Invitation;
import com.tutor93.nikahin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by indraaguslesmana on 6/15/17.
 */

public class HomeGalleryListAdapter extends RecyclerView.Adapter<HomeGalleryListAdapter.GalleryViewHolder> {

    private final List<String> mImageList;
    private OnImageClickListener mOnImageClickListener;

    public HomeGalleryListAdapter(List<String> imageList, OnImageClickListener listener) {
        mImageList = new ArrayList<>();
        mOnImageClickListener = listener;
        addItems(imageList);
    }

    public String getItem(int position) {
        return mImageList.get(position);
    }

    public void addItems(List<String> itemsList) {
        mImageList.addAll(itemsList);
        notifyItemRangeInserted(getItemCount(), mImageList.size() - 1);
    }

    @Override
    public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GalleryViewHolder holder, int position) {
        String imageUrl = mImageList.get(position);
        if (!imageUrl.isEmpty()){
            Glide.with(holder.listItem.getContext())
                    .load(imageUrl)
                    .centerCrop()
                    .into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    class GalleryViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        View listItem;

        GalleryViewHolder(View itemView) {
            super(itemView);
            listItem = itemView;
            image = (ImageView) itemView.findViewById(R.id.home_itemimage);

        }
    }

    public interface OnImageClickListener {

    }
}
