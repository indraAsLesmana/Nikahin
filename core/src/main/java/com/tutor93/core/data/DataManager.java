package com.tutor93.core.data;

import com.tutor93.core.data.model.History;
import com.tutor93.core.data.network.NikahinService;
import com.tutor93.core.data.network.NikahinServiceFactory;
import com.tutor93.core.data.network.RemoteCallback;

import java.util.List;

/**
 * Created by indraaguslesmana on 6/8/17.
 */

public class DataManager {

    public static final String TOKEN = "BoRM1fGNOd8xZEfju3bhIrVH4vaMsiDU";

    private static DataManager sInstance;

    private final NikahinService mNikahinService;

    public static DataManager getInstance() {
        if (sInstance == null) {
            sInstance = new DataManager();
        }
        return sInstance;
    }

    private DataManager() {
        mNikahinService = NikahinServiceFactory.makeNikahinService();
    }

    public void getHistoryList(String token, RemoteCallback<History> listener) {
        mNikahinService.getHistory(token)
                .enqueue(listener);
    }
}
