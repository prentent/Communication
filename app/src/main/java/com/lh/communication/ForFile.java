package com.lh.communication;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lh.communication.bean.Book;
import com.lh.communication.bean.Book2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;

public class ForFile extends AppCompatActivity {

    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_file);
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            Log.e("=====", "sk未挂载");
        } else {
            File directory = Environment.getExternalStorageDirectory();
            file = new File(directory, "ccc.txt");
        }
    }

    public void onClick(View view) {
        Book2 book = new Book2();
        Random random = new Random();
        int anInt = random.nextInt(100);
        book.setName("111" + anInt);
        book.setRice(anInt);
        book.setAddress("222" + anInt);
        if (file == null) return;
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(book);
            Log.e("=======", "写入成功了");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
