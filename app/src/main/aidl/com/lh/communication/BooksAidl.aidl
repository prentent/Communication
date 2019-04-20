// BooksAidl.aidl
package com.lh.communication;

import com.lh.communication.bean.Book;


interface BooksAidl {
        List<Book> getBooks();
        void addBook(in Book book);
}