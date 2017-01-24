package com.moosa.thinkreactive;

import java.util.Comparator;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.internal.util.AppendOnlyLinkedArrayList;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;
import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

/**
 * Created by Moosa on 21/01/2017.
 * moosa.bh@gmail.com
 */

public class Temp {


    private io.reactivex.Observable<Integer> observable;

    /////// Rubbish below
    public void exampleAmbWith() {
        Observable.timer(100, TimeUnit.MILLISECONDS).map(i -> "First")
                .ambWith(Observable.timer(50, TimeUnit.MILLISECONDS).map(i -> "Second"))
                .ambWith(Observable.timer(70, TimeUnit.MILLISECONDS).map(i -> "Third"))
                .subscribe(s -> AppLogs.logd("Result: " + s));

        // Second
//        ConnectableObservable<>
        io.reactivex.Observable.fromIterable(Data.iterable(100))
                .skipWhile(new AppendOnlyLinkedArrayList.NonThrowingPredicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) {
                        return integer < 10;
                    }
                });


        ConnectableObservable<Integer> connectable = ConnectableObservable.fromIterable(Data.iterable(10))
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .publish();


        connectable
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        AppLogs.logd("Mapping : " + Thread.currentThread().getName());
                        return integer + 200;
                    }
                })
                .subscribe(integer -> AppLogs.logd("Integer 1 : " + integer + " - Thread Name: " + Thread.currentThread().getName()));


        connectable
                .sorted(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        AppLogs.logd("Comparision : " + Thread.currentThread().getName());
                        return -(o1.compareTo(o2));
                    }
                })
                .subscribe(integer -> AppLogs.logd("Integer 2 : " + integer + " - Thread Name: " + Thread.currentThread().getName()));

        connectable.connect();

    }


    private void conditionalOperations() {

        Observable.from(Data.iterable(34))
                .skipWhile(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer < 20;
                    }
                })
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        AppLogs.logd("onNext(rx1); -> " + o.toString());
                    }
                });

        observable = io.reactivex.Observable.fromIterable(Data.iterable(34))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .skipWhile(new AppendOnlyLinkedArrayList.NonThrowingPredicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) {

                        return integer < 20;
                    }
                });
        observable.subscribe(new io.reactivex.Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                AppLogs.logd("Dispose: " + d.isDisposed());
            }

            @Override
            public void onNext(Integer integer) {
                AppLogs.logd("onNext(" + integer + ")");
            }

            @Override
            public void onError(Throwable e) {
                AppLogs.logd("" + e.getMessage());
            }

            @Override
            public void onComplete() {
                AppLogs.logd("onComplete();");
                dispose();
            }
        });
    }

    private void dispose() {

    }


    private void scan() {
        //   ArrayList<User> adminUsers = new ArrayList<>();
        //     ArrayList<User> moderator = new ArrayList<>();
        //       ArrayList<User> guest = new ArrayList<>();
//
//
//        Observable   //Schedulers should be define before data manipulation;
//                .from(Data.usersList())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                //.scan(new StringBuilder(), (stringBuilder, user) -> stringBuilder.append(user.getName() + " "))
//                .groupBy(new Func1<User, String>() {
//                    @Override
//                    public String call(User user) {
//
//                        return (user.getLevel() == 0 ? "admin" : (user.getLevel() == 1 ? "guest" : "moderator"));
//                    }
//                })
//
//                .subscribe(users -> {
//                    users.subscribe(user ->
//                            {
//
//                                if (users.getKey().equals("admin")) {
//                                    //admin
//                                    AppLogs.logd("Admin Added in ArrayList: -> " + user.toString());
//                                    adminUsers.add(user);
//                                } else if (users.getKey().equals("guest")) {
//                                    //guest
//                                    guest.add(user);
//                                    AppLogs.logd("Guest Added in ArrayList: -> " + user.toString());
//                                } else {
//                                    //moderator
//                                    moderator.add(user);
//                                    AppLogs.logd("Moderator Added in ArrayList: -> " + user.toString());
//                                }
//
//
//                            }
//                    );
//
//                    //for (User user : users){
//                    AppLogs.logd(users.toString());
//                    // }
//                });
    }


    private void userFiltration() {
//        rx.Observable
//                .from(Data.usersList())
//                .filter(user -> user.getLevel() != User.MODERATOR)
//                .toSortedList((user, user2) -> user.getName().compareTo(user2.getName()))
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(
//                        users ->
//                        {
//                            ArrayList<User> users1 = new ArrayList<User>();
//
//                            for (User u : users) {
//                                u.setEmail(" Not Available");
//                                users1.add(u);
//                            }
//                            return Observable.from(users1);
//                        }
//                )
//                .subscribe(users -> {
//                    //for (User user : users){
//                    AppLogs.logd(users.toString());
//                    // }
//                });
    }


    private void iterable() {
//        rx.Observable<Integer> singleObserable = Observable.from(Data.iterable(10)).subscribeOn(Schedulers.newThread());
//
//        singleObserable.subscribe(integer -> AppLogs.logd("Iterable" + integer));
//
//
//        ArrayList<Integer> arrayList = Data.iterable(30);
//        Subscription ober = Observable
//                .from(arrayList)
//
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//
//                .doOnNext(integer -> {
//                    AppLogs.logd("doOnNext() is On -> " + Thread.currentThread().getName());
//                })
//
//
//                .map(integer -> {
//                    AppLogs.logd("map() is On -> " + Thread.currentThread().getName());
//                    return integer;
//                })
//                .filter(integer -> {
//                    AppLogs.logd("filter() is On -> " + Thread.currentThread().getName());
//                    return integer % 2 == 0;
//                })
//
//
//                .subscribe(integer -> AppLogs.logd("Array " + integer + " subscribe() is On ->" + Thread.currentThread().getName()));
//
    }


}
