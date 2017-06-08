package com.tutor93.core.ui.home;

import com.tutor93.core.ui.base.RemoteView;

/**
 * Created by indraaguslesmana on 6/8/17.
 */

public interface HomeContract {

    interface ViewActions {

        void onHomeBarcodeClick();

        void onHomeSeeallClick();

        void onHomeButtoncenterClick();

        void onHomeImageClick();

    }

    interface HomeClickView extends RemoteView {

    }
}
