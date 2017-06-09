package com.tutor93.core.data.network;

import com.tutor93.core.data.model.History;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by indraaguslesmana on 6/8/17.
 */

public interface NikahinService {

    @GET("v1/history")
    Call<History> getHistory(@Header("Authorize") String token);

}
