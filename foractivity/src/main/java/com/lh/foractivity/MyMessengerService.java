package com.lh.foractivity;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import androidx.annotation.Nullable;

public class MyMessengerService extends Service {

    private static final int MSG_ADD = 1;

    private static class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Message toClient = Message.obtain();
            switch (msg.what) {
                case MSG_ADD:
                    toClient.what = MSG_ADD;
                    toClient.arg1 = msg.arg1 + msg.arg2;
                    try {
                        msg.replyTo.send(toClient);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;

            }
            super.handleMessage(msg);
        }
    }

    private Messenger messenger = new Messenger(new MyHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
