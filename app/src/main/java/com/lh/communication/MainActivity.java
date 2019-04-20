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
        switch (view.getId()) {
            case R.id.button:  //Activity进程间通信
                startActivity(new Intent(this, ForActivity.class));
                break;
            case R.id.button2:  //BroadcastReceiver进程间通信
                startActivity(new Intent(this, ForBroadcastReceiver.class));
                break;
            case R.id.button4:
                startActivity(new Intent(this, ForContentprovider.class));
                break;
            default:
                break;
        }
    }
}
