package com.moosa.thinkreactive.network;

import org.json.JSONObject;

import java.net.URL;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Moosa on 31/01/2017.
 * moosa.bh@gmail.com
 */

public interface PostJSON {

    @Headers("Content-Type: application/json")
    @POST("fcm/jsonpost")
    Call<JSONObject> postJson(@Body JSONObject jsonObject);


    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(URLs.JSON_API_URL)
            .build();
}
