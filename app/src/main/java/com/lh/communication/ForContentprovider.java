package com.lh.communication;

import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ForContentprovider extends AppCompatActivity {

    private EditText ed1;
    private EditText ed2;
    private MyObserver myObserver;
    private Uri parseUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_contentprovider);
        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        parseUri = Uri.parse("content://com.lh.foractivity.provider/teacher");
        myObserver = new MyObserver(new Handler());
        getContentResolver().registerContentObserver(parseUri, true, myObserver);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                Cursor cursor = getContentResolver().query(parseUri, new String[]{"name", "sex"}, "name=?",
                        new String[]{"111"}, "_id DESC");
                while (cursor.moveToNext()) {
                    Log.e("======query", cursor.getString(0) + "---" + cursor.getString(1));
                }
                cursor.close();
                break;
            case R.id.btn2:
                ContentValues values = new ContentValues();
                values.put("name", ed1.getText().toString());
                values.put("sex", ed2.getText().toString());
                Uri insert = getContentResolver().insert(parseUri, values);
                Log.e("=======insert", insert.toString());
                break;
            case R.id.btn3:
                ContentValues values1 = new ContentValues();
                values1.put("sex", "99");
                int update = getContentResolver().update(parseUri, values1, "name=?",
                        new String[]{"111"});
                Log.e("=======update", update + "");
                break;
            case R.id.btn4:
                int delete = getContentResolver().delete(parseUri, "name=?",
                        new String[]{"111"});
                Log.e("=======update", delete + "");
                break;


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(myObserver);
    }

    private final class MyObserver extends ContentObserver {
        public MyObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);
            Log.e("=========Observer", selfChange + "---" + uri);
        }
    }
}
