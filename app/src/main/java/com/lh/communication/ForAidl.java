package com.lh.communication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lh.communication.bean.Book;

import java.util.Random;

public class ForAidl extends AppCompatActivity {

    private BooksAidl booksAidl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_aidl);
        bindServiceInvoke();

    }

    private void bindServiceInvoke() {
        Intent intent = new Intent("com.lb.aidlservice");
        intent.setPackage("com.lh.foractivity");
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            booksAidl = BooksAidl.Stub.asInterface(service);
            Log.e("=======", "链接成功了");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            booksAidl = null;
            Log.e("=======", "断开链接了");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addBook:
                Book book = new Book();
                Random random = new Random();
                int anInt = random.nextInt(100);
                book.setName("111" + anInt);
                book.setRice(anInt);
                book.setAddress("222" + anInt);
                try {
                    booksAidl.addBook(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.getBooks:
                try {
                    (booksAidl.getBooks()).forEach(item -> {
                        Log.e("=======", item.getName() + "---" + item.getRice());
                    });
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }

    }
}
