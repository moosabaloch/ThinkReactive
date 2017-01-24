package com.moosa.thinkreactive;

import java.util.ArrayList;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by Moosa on 22/01/2017.
 * moosa.bh@gmail.com
 */

public class UserList extends ArrayList<User> implements ObservableOnSubscribe<User> {
    private ObservableEmitter<User> emitter;

    public UserList() {
        add(new User(0, " -- ", " -- "));

    }

    @Override
    public boolean add(User user) {
        if (emitter != null) {
            emitter.onNext(user);
        }
        return super.add(user);
    }


    @Override
    public void subscribe(ObservableEmitter<User> e) throws Exception {
        this.emitter = e;
    }
}
