package com.tutor93.core.ui.home;

import com.tutor93.core.data.DataManager;
import com.tutor93.core.data.model.History;
import com.tutor93.core.data.network.RemoteCallback;
import com.tutor93.core.ui.base.BasePresenter;

/**
 * Created by indraaguslesmana on 6/8/17.
 */

public class HomePresenter extends BasePresenter<HomeContract.HomeClickView> implements
        HomeContract.ViewActions {

    private DataManager mDataManager;
    private History mHistory;

    public HomePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void onHistoryRequest(String token) {
        getHistory(token);
    }

    private void getHistory(String token) {
        if (!isViewAttached()) return;

        mDataManager.getHistoryList(token, new RemoteCallback<History>() {
            @Override
            public void onSuccess(History response) {
                mView.showHistoryList(response.invitations);
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
