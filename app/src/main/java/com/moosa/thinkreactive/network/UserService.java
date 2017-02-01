package com.moosa.thinkreactive.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.moosa.thinkreactive.AppLogs;
import com.moosa.thinkreactive.model.users.User;

import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Moosa on 01/02/2017.
 * moosa.bh@gmail.com
 */

public class UserService {
    private GetUser getUsers;

    public UserService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getUsers = retrofit.create(GetUser.class);
    }

    public Single<List<User>> getUsersSortedByName() {
        return getUsers.getAllUsers()
                .flatMapIterable(new Function<List<User>, Iterable<User>>() {
                    @Override
                    public Iterable<User> apply(List<User> users) throws Exception {
                        return users;
                    }
                })
                .flatMap(user -> {
                    AppLogs.logd("Requesting User for User ID = " + user.getId());
                    return getUserById(user.getId());
                })
                .toSortedList(new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<User> getUserById(int id) {
        return getUsers.getUserById(id)
                .map(new Function<User, User>() {
                    @Override
                    public User apply(User user) throws Exception {
                        user.setEmail("Altered");
                        return user;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
