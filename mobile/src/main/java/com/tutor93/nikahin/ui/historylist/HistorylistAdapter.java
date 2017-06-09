package com.tutor93.nikahin.ui.historylist;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tutor93.core.data.model.History;
import com.tutor93.nikahin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by indraaguslesmana on 6/9/17.
 */

public class HistorylistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final List<History.Invitation> mInvitationList;
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

    public HistorylistAdapter() {
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
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character_gallery, parent, false);
                break;
            // it can add if new view devine
        }
        return new CharacterViewHolder(view);
    }

    private void onBindGenericItemViewHolder(final CharacterViewHolder holder, int position) {
        holder.name.setText(mInvitationList.get(position).invitationTitle);

        String characterImageUrl = mInvitationList.get(position).invitationImage;
        if (!TextUtils.isEmpty(characterImageUrl)) {
            Picasso.with(holder.listItem.getContext())
                    .load(characterImageUrl)
                    .centerCrop()
                    .fit()
                    .into(holder.image);
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
        public final TextView name;
        public final AppCompatImageView image;

        public CharacterViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
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

    /**
     * Interface for handling list interactions
     */
    public interface InteractionListener {
        void onListClick(History.Invitation invitation, View sharedElementView, int adapterPosition);
    }

    public void setHistorylistInteractionListener(InteractionListener listInteractionListener) {
        mHistorylistInteractionListener = listInteractionListener;
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void removeAll() {
        mInvitationList.clear();
        notifyDataSetChanged();
    }

    public void addItems(List<History.Invitation> itemsList) {
        mInvitationList.addAll(itemsList);
        notifyDataSetChanged();
        notifyItemRangeInserted(getItemCount(), mInvitationList.size() - 1);
    }
}
