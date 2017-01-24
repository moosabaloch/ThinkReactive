package com.moosa.thinkreactive;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Moosa on 21/01/2017.
 * moosa.bh@gmail.com
 */

public class TestDatabase extends SQLiteOpenHelper {

    public TestDatabase(Context context) {
        super(context, "test.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}
