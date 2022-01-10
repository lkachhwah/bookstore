package com.example.bookstore.repository;

import com.example.bookstore.dao.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book,Long> {
    public List<Book> findByAuthorName(String authorName );
    public Book findByBookName(String bookName);
}
