package com.tutor93.core.ui.invitationlist;

import com.tutor93.core.data.model.History;
import com.tutor93.core.data.model.Invitation;
import com.tutor93.core.ui.base.RemoteView;

import java.util.List;

/**
 * Created by indraaguslesmana on 6/9/17.
 */

public interface InvitationlistContract {

    interface ViewActions {

        void onInitialListRequested(String token);

    }

    interface HistorylistView extends RemoteView {

        void showHistorylist(List<Invitation> invitations);
    }
}
