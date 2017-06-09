package com.tutor93.core.ui.historylist;

import com.tutor93.core.data.model.History;
import com.tutor93.core.ui.base.RemoteView;

import java.util.List;

/**
 * Created by indraaguslesmana on 6/9/17.
 */

public interface HistorylistContract {

    interface ViewActions {

        void onInitialListRequested(String token);

    }

    interface HistorylistView extends RemoteView {

        void showHistorylist(List<History.Invitation> invitations);
    }
}
