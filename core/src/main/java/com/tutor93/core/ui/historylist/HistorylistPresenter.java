package com.tutor93.core.ui.historylist;

import android.support.annotation.NonNull;

import com.tutor93.core.data.DataManager;
import com.tutor93.core.data.model.History;
import com.tutor93.core.data.network.RemoteCallback;
import com.tutor93.core.ui.base.BasePresenter;

import java.util.List;

/**
 * Created by indraaguslesmana on 6/9/17.
 */

public class HistorylistPresenter extends BasePresenter<HistorylistContract.HistorylistView>
        implements HistorylistContract.ViewActions {

    private final DataManager mDataManager;

    public HistorylistPresenter(@NonNull DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void onInitialListRequested(String token) {
        getHistoryInvitation(token);
    }

    private void getHistoryInvitation(String token) {

        mDataManager.getHistoryList(token, new RemoteCallback<History>() {
            @Override
            public void onSuccess(History response) {
                if (!isViewAttached()) return;
                mView.hideProgress();
                List<History.Invitation> responseResults = response.invitations;
                if (responseResults.isEmpty()) {
                    mView.showEmpty();
                    return;
                }

                mView.showHistorylist(responseResults);

            }

            @Override
            public void onUnauthorized() {

            }

            @Override
            public void onFailed(Throwable throwable) {

            }
        });

    }
}
