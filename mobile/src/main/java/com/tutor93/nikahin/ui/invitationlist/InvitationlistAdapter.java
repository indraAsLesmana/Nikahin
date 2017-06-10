package com.tutor93.nikahin.ui.invitationlist;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.squareup.picasso.Picasso;
import com.tutor93.core.data.model.History;
import com.tutor93.core.data.model.Invitation;
import com.tutor93.nikahin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by indraaguslesmana on 6/9/17.
 */

public class InvitationlistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final List<Invitation> mInvitationList;
    private InteractionListener mHistorylistInteractionListener;

    /**
     * ViewTypes serve as a mapping point to which layout should be inflated
     */
    public static final int VIEW_TYPE_GALLERY = 0;
    public static final int VIEW_TYPE_LIST = 1;
    public static final int VIEW_TYPE_LOADING = 2;

    @interface ViewType {
    }

    @ViewType
    private int mViewType;

    public InvitationlistAdapter() {
        mInvitationList = new ArrayList<>();
        mViewType = VIEW_TYPE_GALLERY;
        mHistorylistInteractionListener = null;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_LOADING) {
            return onIndicationViewHolder(parent);
        }
        return onGenericItemViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_TYPE_LOADING) {
            return;
        }
        onBindGenericItemViewHolder((CharacterViewHolder) holder, position);
    }

    private RecyclerView.ViewHolder onIndicationViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress_bar, parent, false);
        return new ProgressBarViewHolder(view);
    }

    private RecyclerView.ViewHolder onGenericItemViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case VIEW_TYPE_GALLERY:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invitation_gallery, parent, false);
                break;
            // it can add if new view devine
        }
        return new CharacterViewHolder(view);
    }

    private void onBindGenericItemViewHolder(final CharacterViewHolder holder, int position) {
        holder.name.setText(mInvitationList.get(position).invitationTitle);
        holder.date.setText(mInvitationList.get(position).invitationDate);
        String invitationImage = mInvitationList.get(position).invitationImage;

        if (!TextUtils.isEmpty(invitationImage)) {
            Glide.with(holder.listItem.getContext())
                    .load(invitationImage)
                    .asBitmap()
                    .centerCrop()
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(new SimpleTarget<Bitmap>(800, 480) {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                        BitmapDrawable bitmap1 = new
                                BitmapDrawable(holder.listItem.getResources(), bitmap);
                        holder.image.setBackground(bitmap1);
                    }
                });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mInvitationList.get(position) == null ? VIEW_TYPE_LOADING : mViewType;
    }

    @Override
    public int getItemCount() {
        return mInvitationList.size();
    }

    public void setViewType(@ViewType int mViewType) {
        this.mViewType = mViewType;
    }

    public int getViewType() {
        return mViewType;
    }

    public boolean addLoadingView() {
        if (getItemViewType(mInvitationList.size() - 1) != VIEW_TYPE_LOADING) {
            add(null);
            return true;
        }
        return false;
    }

    public void add(Invitation item) {
        add(null, item);
    }

    public void add(@Nullable Integer position, Invitation item) {
        if (position != null) {
            mInvitationList.add(position, item);
            notifyItemInserted(position);
        } else {
            mInvitationList.add(item);
            notifyItemInserted(mInvitationList.size() - 1);
        }
    }

    /**
     * ViewHolders
     */
    public class ProgressBarViewHolder extends RecyclerView.ViewHolder {

        public final ProgressBar progressBar;

        public ProgressBarViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        }
    }

    public class CharacterViewHolder extends RecyclerView.ViewHolder {

        public final View listItem;
        public final TextView name, date;
        public final AppCompatImageView image;

        public CharacterViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            date = (TextView) view.findViewById(R.id.date);
            image = (AppCompatImageView) view.findViewById(R.id.image);
            listItem = view;
            listItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mHistorylistInteractionListener != null) {
                        mHistorylistInteractionListener.onListClick(mInvitationList.get(getAdapterPosition()),
                                image, getAdapterPosition());
                    }
                }
            });
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void removeAll() {
        mInvitationList.clear();
        notifyDataSetChanged();
    }

    public void addItems(List<Invitation> itemsList) {
        mInvitationList.addAll(itemsList);
        notifyItemRangeInserted(getItemCount(), mInvitationList.size() - 1);
    }

    /**
     * Interface for handling list interactions
     */
    public interface InteractionListener {
        void onListClick(Invitation invitation, View sharedElementView, int adapterPosition);
    }

    public void setHistorylistInteractionListener(InteractionListener listInteractionListener) {
        mHistorylistInteractionListener = listInteractionListener;
    }
}
