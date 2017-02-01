package com.moosa.thinkreactive;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

import java.util.Comparator;
import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
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
        //conditionalOperations();
        //userFiltration();
        //iterable();
        //exampleAmb();
        //scan();
        //autoChange();
        //autoChange();
        //exampleAmb();
        //gestureRecognizer();

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

    private void gestureRecognizer() {
        io.reactivex.Observable.create(new ObservableOnSubscribe<MotionEvent>() {
            @Override
            public void subscribe(ObservableEmitter<MotionEvent> e) throws Exception {
                View.OnTouchListener touchListener = new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        e.onNext(event);
                        return false;
                    }
                };
                View.OnDragListener dragListener = new View.OnDragListener() {
                    @Override
                    public boolean onDrag(View v, DragEvent event) {
                        AppLogs.logd("");
                        return true;
                    }
                };
                // view.setOnDragListener(dragListener);
            }
        }).subscribe(new io.reactivex.Observer<MotionEvent>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MotionEvent motionEvent) {
                AppLogs.logd("Motion Event: X->" + motionEvent.getAxisValue(MotionEvent.AXIS_X) + "| Y->" + motionEvent.getAxisValue(MotionEvent.AXIS_X) + "| ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void autoChange() throws Exception {
        UserList users = new UserList();
        io.reactivex.Observable.create(users)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new io.reactivex.Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        AppLogs.logd("New User Added: " + user.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        final int[] i = {0};

        io.reactivex.Observable.fromIterable(Data.usersList())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new io.reactivex.Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        try {
                            i[0]++;
                            users.add(new User(0, "New User" + i[0], "----"));
                            Thread.sleep(1000);
                        } catch (Exception ecx) {
                            AppLogs.logd("Error Adding User ");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void exampleAmb() {
//        createTextChangeObservable().observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        AppLogs.logd("onSubscribe(Disposable d);");
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        AppLogs.logd("New String is: "+s);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        AppLogs.logd("onComplete();");
//
//                    }
//                });
//        Observable<User> userObservable = Observable.fromIterable(Data.usersList())
//                .sorted(new Comparator<User>() {
//                    @Override
//                    public int compare(User o1, User o2) {
//                        return (o1.getName().compareTo(o2.getName()));
//                    }
//                });
//
//
//        userObservable.subscribe(new Observer<User>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                AppLogs.logd("onSubscribe();");
//            }
//
//            @Override
//            public void onNext(User user) {
//                AppLogs.logd("onNext();");
//                AppLogs.logd("Print: " + user.toString());
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                AppLogs.logd("onError();");
//            }
//
//            @Override
//            public void onComplete() {
//                AppLogs.logd("onComplete();");
//            }
//        });
//

    }

    //1
    private io.reactivex.Observable<String> createTextChangeObservable() {
        //2
        io.reactivex.Observable<String> textChangeObservable = io.reactivex.Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                //3
                final TextWatcher watcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }

                    //4
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        emitter.onNext(s.toString());
                    }
                };

                //5
                //   completeTextView.addTextChangedListener(watcher);

                emitter.setDisposable(new Disposable() {
                    @Override
                    public void dispose() {

                    }

                    @Override
                    public boolean isDisposed() {
                        return false;
                    }
                });
                //6

                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        //        completeTextView.removeTextChangedListener(watcher);
                    }
                });
            }
        });

        // 7
        return textChangeObservable.filter(new Predicate<String>() {
            @Override
            public boolean test(String s) throws Exception {
                return s.length() > 2;
            }
        });
    }
}
