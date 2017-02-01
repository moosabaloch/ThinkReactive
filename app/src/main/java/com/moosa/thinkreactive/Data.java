package com.moosa.thinkreactive;

import java.util.ArrayList;

/**
 * Created by Moosa on 18/01/2017.
 * moosa.bh@gmail.com
 */

public class Data {

    public static ArrayList<User> usersList() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(User.ADMIN, "Muhammad Moosa", "moosa.bh@gmail.com"));
        users.add(new User(User.GUEST, "Kamran Ali", "kamran@gmail.com"));
        users.add(new User(User.MODERATOR, "Muhammad Imran", "imran@gmail.com"));
        users.add(new User(User.ADMIN, "Muhammad Qasim", "qasim@gmail.com"));
        users.add(new User(User.MODERATOR, "Babar Raza", "inter@gmail.com"));
        users.add(new User(User.GUEST, "Umer Nawaz", "dashing@gmail.com"));
        users.add(new User(User.ADMIN, "Danish Soomro", "soomro@gmail.com"));
        users.add(new User(User.MODERATOR, "Ali Rana", "alir@gmail.com"));
        users.add(new User(User.ADMIN, "Faiz Rehman", "faiz@gmail.com"));
        return users;
    }


    public static ArrayList<Integer> iterable(int length) {
        ArrayList<Integer> integers = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            integers.add(i);
        }
        return integers;
    }

    public static Integer[] primitive(int length) {
        return (Integer[]) iterable(length).toArray();
    }
}
