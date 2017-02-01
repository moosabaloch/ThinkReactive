package com.moosa.thinkreactive.network;

import com.moosa.thinkreactive.model.users.User;

import java.util.List;
import java.util.Observer;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by Moosa on 31/01/2017.
 * moosa.bh@gmail.com
 */

public interface GetUser {

    @GET("users")
    Observable<List<User>> getAllUsers();

    @GET("users/{id}")
    Observable<User> getUserById(@Path("id") int id);
}
