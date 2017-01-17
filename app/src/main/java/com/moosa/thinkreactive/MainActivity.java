package com.moosa.thinkreactive;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView);

//"one", "two", "three", "four", "five"
        Observable.from(new Future<String>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public String get() throws InterruptedException, ExecutionException {
                String a = "asdfas";
                AppLogs.logd("Network Req Start");
                Thread.sleep(5000);
                AppLogs.logd("Network End" + a);
                tv.setText(a);
                return a;
            }

            @Override
            public String get(long timeout, @NonNull TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                AppLogs.logd("get(" + timeout + "," + unit + ");");

                return "getTimeOut+unit";
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .map(new Func1<String, HashMap<String, String>>() {
                    @Override
                    public HashMap<String, String> call(String s) {
                        HashMap<String, String> val = new HashMap<String, String>();
                        val.put("kuch b", s);
                        return val;
                    }
                })
                .subscribe(new Observer<HashMap<String, String>>() {
                    @Override
                    public void onCompleted() {
                        AppLogs.logd("onCompleted();");
                    }

                    @Override
                    public void onError(Throwable e) {
                        AppLogs.logd("onError(); -> " + e.getMessage());
                    }

                    @Override
                    public void onNext(HashMap<String, String> s) {
                        AppLogs.logd("onNext(); -> " + s.toString());
                        tv.setText(s.toString());
                    }
                });


    }


}
