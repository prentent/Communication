package com.lh.communication;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void onClick(View view) {
        startActivityC1();
        // startActivityC2();
    }

    //隐式启动方式
    private void startActivityC1() {
        Intent intent = new Intent("com.lh.test");
        intent.putExtra("name", "传递过来的值");
        startActivity(intent);

    }

    private void startActivityC2() {
        ComponentName component = new ComponentName("com.lh.foractivity", "com.lh.foractivity.MainActivity");
        Intent intent = new Intent();
        intent.putExtra("name", "传递过来的值");
        intent.setComponent(component);
        startActivity(intent);
    }

    public void onClickProiver(View view) {
        startActivity(new Intent(this, ForContentprovider.class));
    }
}
