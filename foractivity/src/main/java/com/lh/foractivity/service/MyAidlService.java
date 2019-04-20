package com.lh.foractivity.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.lh.communication.BooksAidl;
import com.lh.communication.bean.Book;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class MyAidlService extends Service {

    private List<Book> books = new ArrayList<Book>();

    private BooksAidl.Stub booksAidl = new BooksAidl.Stub() {
        @Override
        public List<Book> getBooks() {
            return books;
        }

        @Override
        public void addBook(Book book) {
            if (book == null) return;
            books.add(book);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return booksAidl;
    }
}
