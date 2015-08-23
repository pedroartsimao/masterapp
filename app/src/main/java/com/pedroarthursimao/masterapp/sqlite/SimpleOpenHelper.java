package com.pedroarthursimao.masterapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SimpleOpenHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "test.sqlite";
    public static final int DB_VERSION = 1;

    public SimpleOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE test ( `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `sometext` TEXT NULL)");
        ContentValues cv = new ContentValues();
        cv.put("sometext", "Value 1");
        db.insert("test", null, cv);
        Log.i("CREATE SQLITE", "Successfully created!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

}
