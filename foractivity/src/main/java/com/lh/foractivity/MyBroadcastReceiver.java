package com.lh.foractivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) return;
        //接受广播
        if (intent.getAction().equals("com.lh.receiver")) {
            String data = intent.getStringExtra("data");
            Log.e("===", data);
            Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
        }
    }
}
