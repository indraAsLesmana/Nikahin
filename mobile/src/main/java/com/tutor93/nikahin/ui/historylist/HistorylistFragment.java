package com.tutor93.nikahin.ui.historylist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tutor93.core.data.DataManager;
import com.tutor93.core.data.model.History;
import com.tutor93.core.ui.historylist.HistorylistContract;
import com.tutor93.core.ui.historylist.HistorylistPresenter;
import com.tutor93.nikahin.R;
import com.tutor93.nikahin.ui.home.HomeFragment;
import com.tutor93.nikahin.util.Constant;
import com.tutor93.nikahin.util.DisplayMetricsUtil;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistorylistFragment extends Fragment implements HistorylistContract.HistorylistView,
        HistorylistAdapter.InteractionListener, SwipeRefreshLayout.OnRefreshListener {

    private static final int TAB_LAYOUT_SPAN_SIZE = 2;
    private static final int TAB_LAYOUT_ITEM_SPAN_SIZE = 1;
    private static final int SCREEN_TABLET_DP_WIDTH = 600;

    private AppCompatActivity mActivity;
    private HistorylistPresenter mHistorylistPresenter;
    private HistorylistAdapter mHistorylistAdapter;

    private RecyclerView mHistorysRecycler;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ProgressBar mContentLoadingProgress;

    private View mMessageLayout;
    private ImageView mMessageImage;
    private TextView mMessageText;
    private Button mMessageButton;


    public static HistorylistFragment newInstance() {
        return new HistorylistFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHistorylistPresenter = new HistorylistPresenter(DataManager.getInstance());
        mHistorylistAdapter = new HistorylistAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_historylist, container, false);

        initViews(view);
        mHistorylistPresenter.attachView(this);
        mHistorylistAdapter.setHistorylistInteractionListener(this);
        if (mHistorylistAdapter.isEmpty()) {
            mHistorylistPresenter.onInitialListRequested(Constant.TOKEN);
        }
        return view;
    }

    private void initViews(View view) {
        mActivity = (AppCompatActivity) getActivity();
//        mActivity.setSupportActionBar((Toolbar) view.findViewById(R.id.toolbar));

        mHistorysRecycler = (RecyclerView) view.findViewById(R.id.recycler_historylist);
        mHistorysRecycler.setHasFixedSize(true);
        mHistorysRecycler.setMotionEventSplittingEnabled(false);
        mHistorysRecycler.setItemAnimator(new DefaultItemAnimator());
        mHistorysRecycler.setAdapter(mHistorylistAdapter);

        boolean isTabletLayout = DisplayMetricsUtil.isScreenW(SCREEN_TABLET_DP_WIDTH);
        mHistorysRecycler.setLayoutManager(setUpLayoutManager(isTabletLayout));
        // need to solve behavior of this full to refresh
        /*mHistorysRecycler.addOnScrollListener(setupScrollListener(isTabletLayout,
                mHistorysRecycler.getLayoutManager()));*/

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_to_refresh);
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.icons);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mContentLoadingProgress = (ProgressBar) view.findViewById(R.id.progress);
        mMessageLayout = view.findViewById(R.id.message_layout);
        mMessageImage = (ImageView) view.findViewById(R.id.iv_message);
        mMessageText = (TextView) view.findViewById(R.id.tv_message);
        mMessageButton = (Button) view.findViewById(R.id.btn_try_again);
        mMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRefresh();
            }
        });

    }

    private RecyclerView.LayoutManager setUpLayoutManager(boolean isTabletLayout) {
        RecyclerView.LayoutManager layoutManager;
        if (!isTabletLayout) {
            layoutManager = new LinearLayoutManager(mActivity);
        } else {
            layoutManager = initGridLayoutManager(TAB_LAYOUT_SPAN_SIZE, TAB_LAYOUT_ITEM_SPAN_SIZE);
        }
        return layoutManager;
    }

    private RecyclerView.LayoutManager initGridLayoutManager(final int spanCount, final int itemSpanCount) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, spanCount);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (mHistorylistAdapter.getItemViewType(position)) {
                    case HistorylistAdapter.VIEW_TYPE_LOADING:
                        // If it is a loading view we wish to accomplish a single item per row
                        return spanCount;
                    default:
                        // Else, define the number of items per row (considering TAB_LAYOUT_SPAN_SIZE).
                        return itemSpanCount;
                }
            }
        });
        return gridLayoutManager;
    }

    private EndlessRecyclerViewOnScrollListener setupScrollListener(boolean isTabletLayout,
                                                                    RecyclerView.LayoutManager layoutManager) {
        return new EndlessRecyclerViewOnScrollListener(isTabletLayout ?
                (GridLayoutManager) layoutManager : (LinearLayoutManager) layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (mHistorylistAdapter.addLoadingView()) {
//                    onRefresh();
                    // need change behavior of pull to refresh
                }
            }
        };
    }


    @Override
    public void showHistorylist(List<History.Invitation> invitations) {
        if (mHistorylistAdapter.getViewType() != HistorylistAdapter.VIEW_TYPE_GALLERY) {
            mHistorylistAdapter.removeAll();
            mHistorylistAdapter.setViewType(HistorylistAdapter.VIEW_TYPE_GALLERY);
        }

        if (!mSwipeRefreshLayout.isActivated()) {
            mSwipeRefreshLayout.setEnabled(true);
        }

        mHistorylistAdapter.addItems(invitations);
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
    public void onListClick(History.Invitation invitation, View sharedElementView, int adapterPosition) {

    }

    @Override
    public void onRefresh() {
        mHistorylistAdapter.removeAll();
        mHistorylistPresenter.onInitialListRequested(Constant.TOKEN);
    }
}
