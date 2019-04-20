package com.lh.communication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.Random;

public class ForMessenger extends AppCompatActivity {

    private static final int MSG_ADD = 1;
    private Messenger messenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_messenger);
        bindServiceInvoke();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
            Log.e("=======", "链接成功了");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            messenger = null;
            Log.e("=======", "断开链接了");
        }
    };

    private static class MyHandler extends Handler {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_ADD:
                    Log.e("=========", String.valueOf(msg.arg1));
                    break;
            }
            super.handleMessage(msg);
        }
    }

    private Messenger myMessenger = new Messenger(new MyHandler());

    private void bindServiceInvoke() {
        Intent intent = new Intent("com.lb.servicemessenger");
        intent.setPackage("com.lh.foractivity");
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private int a = 0;

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                Random random = new Random();
                int anInt = random.nextInt(100);
                Message message = new Message();
                message.what = MSG_ADD;
                message.arg1 = anInt;
                message.arg2 = a++;
                message.replyTo = myMessenger;
                try {
                    messenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
