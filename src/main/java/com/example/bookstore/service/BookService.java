package com.example.bookstore.service;

import com.example.bookstore.dao.Book;

public interface BookService {
    public Book addBook(Book book);
    public int updateBookQuantity(int quantity,long id);
    public boolean bookPurchased(long Id,int quantity );
}
