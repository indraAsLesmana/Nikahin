package com.tutor93.core.ui.home;

import com.tutor93.core.data.model.History;
import com.tutor93.core.ui.base.RemoteView;

import java.util.List;

/**
 * Created by indraaguslesmana on 6/8/17.
 */

public interface HomeContract {

    interface ViewActions {

        void onHistoryRequest (String token);
    }

    interface HomeClickView extends RemoteView {

        void showHistoryList (List<History.Invitation> histories);
    }
}
