package com.moosa.thinkreactive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private Button button;
    private AutoCompleteTextView completeTextView;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.click_button);
        completeTextView = (AutoCompleteTextView) findViewById(R.id.autoComplete);
        view = findViewById(R.id.gesture);

        //conditionalOperations();
        //userFiltration();
        //iterable();
        // exampleAmb();
        //scan();
        try {
            //autoChange();
            autoChange();
            exampleAmb();
            gestureRecognizer();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void gestureRecognizer() {
        Observable.create(new ObservableOnSubscribe<MotionEvent>() {
            @Override
            public void subscribe(ObservableEmitter<MotionEvent> e) throws Exception {
                View.OnTouchListener touchListener =  new View.OnTouchListener(){

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
                view.setOnDragListener(dragListener);
            }
        }).subscribe(new Observer<MotionEvent>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MotionEvent motionEvent) {
                AppLogs.logd("Motion Event: X->"+motionEvent.getAxisValue(MotionEvent.AXIS_X)+"| Y->"+motionEvent.getAxisValue(MotionEvent.AXIS_X)+"| ");
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
        Observable.create(users)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<User>() {
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

        Observable.fromIterable(Data.usersList())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<User>() {
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
    private Observable<String> createTextChangeObservable() {
        //2
        Observable<String> textChangeObservable = Observable.create(new ObservableOnSubscribe<String>() {
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
                completeTextView.addTextChangedListener(watcher);

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
                        completeTextView.removeTextChangedListener(watcher);
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
