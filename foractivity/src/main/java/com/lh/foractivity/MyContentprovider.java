package com.lh.foractivity;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lh.foractivity.sqlite.MySqlLite;

public class MyContentprovider extends ContentProvider {

    private MySqlLite mySqlLite = null;

    private static UriMatcher uriMatcher = null;

    private static final int CODE_TEACHER = 1;
    private static final int CODE_STUDENT = 2;
    private static final String CONTENT_TEACHER_TYPE = "vnd.android.cursor.dir/vnd.example.teacher";
    private static final String CONTENT_STUDENT_TYPE = "vnd.android.cursor.dir/vnd.example.student";
    private static final String CONTENT_TEACHER = "teacher";
    private static final String CONTENT_STUDENT = "student";

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.lh.foractivity.provider", "teacher", CODE_TEACHER);
        uriMatcher.addURI("com.lh.foractivity.provider", "student", CODE_STUDENT);
    }

    @Override
    public boolean onCreate() {
        mySqlLite = new MySqlLite(getContext());
        return true;
    }

    @androidx.annotation.Nullable
    @Nullable
    @Override
    public Cursor query(@androidx.annotation.NonNull @NonNull Uri uri, @androidx.annotation.Nullable @Nullable String[] projection,
                        @androidx.annotation.Nullable @Nullable String selection, @androidx.annotation.Nullable @Nullable String[] selectionArgs,
                        @androidx.annotation.Nullable @Nullable String sortOrder) {
        String tableName = getTableName(uri);
        SQLiteDatabase db = mySqlLite.getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(tableName);
        //db.query(table,projection,selection,selectionArgs,null,null,sortOrder,null);
        //包装一次
        Cursor query = builder.query(db, projection, selection, selectionArgs, null, null, sortOrder, null);
        return query;
    }

    @androidx.annotation.Nullable
    @Nullable
    @Override
    public String getType(@androidx.annotation.NonNull @NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case CODE_TEACHER:
                return CONTENT_TEACHER_TYPE;
            case CODE_STUDENT:
                return CONTENT_STUDENT_TYPE;
        }
        return null;
    }

    @androidx.annotation.Nullable
    @Nullable
    @Override
    public Uri insert(@androidx.annotation.NonNull @NonNull Uri uri, @androidx.annotation.Nullable @Nullable ContentValues contentValues) {
        String tableName = getTableName(uri);
        SQLiteDatabase db = mySqlLite.getWritableDatabase();
        long insert = db.insert(tableName, null, contentValues);
        db.close();
        if (insert > 0) {
            Uri uri1 = ContentUris.withAppendedId(uri, insert);
            getContext().getContentResolver().notifyChange(uri1, null);
            return uri1;
        }
        return null;
    }

    @Override
    public int delete(@androidx.annotation.NonNull @NonNull Uri uri, @androidx.annotation.Nullable @Nullable String s, @androidx.annotation.Nullable @Nullable String[] strings) {
        String tableName = getTableName(uri);
        SQLiteDatabase db = mySqlLite.getWritableDatabase();
        int delete = db.delete(tableName, s, strings);
        db.close();
        getContext().getContentResolver().notifyChange(uri, null);
        return delete;
    }

    @Override
    public int update(@androidx.annotation.NonNull @NonNull Uri uri, @androidx.annotation.Nullable @Nullable ContentValues contentValues, @androidx.annotation.Nullable @Nullable String s, @androidx.annotation.Nullable @Nullable String[] strings) {
        String tableName = getTableName(uri);
        SQLiteDatabase db = mySqlLite.getWritableDatabase();
        int update = db.update(tableName, contentValues, s, strings);
        db.close();
        getContext().getContentResolver().notifyChange(uri, null);

        return update;
    }

    private String getTableName(@androidx.annotation.NonNull @NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case CODE_TEACHER:
                return CONTENT_TEACHER;
            case CODE_STUDENT:
                return CONTENT_STUDENT;
        }
        return null;
    }
}
