package com.lh.foractivity.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySqlLite extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "lh.db";
    private static final int VERSION = 1;

    public MySqlLite(@Nullable @android.support.annotation.Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE teacher(_id integer PRIMARY KEY AUTOINCREMENT ,name VARCHAR(50),sex VARCHAR(10))");
        sqLiteDatabase.execSQL("CREATE TABLE student(_id integer PRIMARY KEY AUTOINCREMENT ,name VARCHAR(50),sex VARCHAR(10))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i == 1 && i1 == 2) {   //说明是从1.0向2.0进行升级
            //增加一个列
            sqLiteDatabase.execSQL("ALTER TABLE teacher ADD age Integer");
            sqLiteDatabase.execSQL("ALTER TABLE student ADD teacher_id Integer");
        }
    }
}
