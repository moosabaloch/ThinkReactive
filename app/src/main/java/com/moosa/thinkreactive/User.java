package com.moosa.thinkreactive;

import java.util.Observer;

import rx.Observable;

/**
 * Created by Moosa on 19/01/2017.
 * moosa.bh@gmail.com
 */

public class User extends io.reactivex.Observable{
    public static final int ADMIN = 0;
    public static final int MODERATOR = 2;
    public static final int GUEST = 1;
    private int level;
    private String name;
    private String email;

    public User(int level, String name, String email) {

        this.level = level;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "level=" + (level == 0 ? "Admin" : (level == 1 ? "Guest" : "Moderator")) +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';

    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    protected void subscribeActual(io.reactivex.Observer observer) {

    }
}
