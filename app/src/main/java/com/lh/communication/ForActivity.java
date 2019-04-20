package com.lh.communication;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ForActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for);
    }


    public void onClick(View view) {
        startActivityD1();
//        startActivityD2();
//        startActivityD3();
    }

    //显示意图 打电话
    private void startActivityD3() {
        Uri uri = Uri.parse("tel:10086");
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(intent);
    }

    //显示跳转
    private void startActivityD2() {
        ComponentName component = new ComponentName("com.lh.foractivity",
                "com.lh.foractivity.MainActivity");
        Intent intent = new Intent();
        intent.setComponent(component);
        intent.putExtra("data", "我是communicationdemo传递过来的值哦~");
        startActivity(intent);
    }

    //隐式跳转
    private void startActivityD1() {
        Intent intent = new Intent("com.lh.test");
        intent.putExtra("data", "我是communicationdemo传递过来的值哦~");
        startActivity(intent);
    }

}
