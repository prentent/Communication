package com.lh.communication;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ForBroadcastReceiver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_broadcast_receiver);
    }

    //广播发送数据
    public void onClick(View view) {
        ComponentName component = new ComponentName("com.lh.foractivity",
                "com.lh.foractivity.MyBroadcastReceiver");
        Intent intent = new Intent("com.lh.receiver");
        intent.setComponent(component);
        intent.putExtra("data", "我是发送的广播");
        sendBroadcast(intent);
    }

}
