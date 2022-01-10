package com.example.bookstore.service.impl;

import com.example.bookstore.dao.Book;
import com.example.bookstore.enums.ErrorCode;
import com.example.bookstore.exception.BookValidationException;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.NextSequenceService;
import com.example.bookstore.utils.ValidationsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    NextSequenceService nextSequenceService;

    @Autowired
    ValidationsUtil validationsUtil;

    @Override
    public Book addBook(Book book) {
        validationsUtil.validateBook(book);
        validateBookNameAlreadyExist(book);
        book.setId(nextSequenceService.getNextSequence("book"));
        return bookRepository.save(book);
    }

    private void validateBookNameAlreadyExist(Book book) {
        if (Objects.nonNull(bookRepository.findByBookName(book.getBookName()))) {
            throw new BookValidationException(ErrorCode.BOOK_NAME_ALREADY_USED.getCode(), ErrorCode.BOOK_NAME_ALREADY_USED.getMessage());
        }
    }

    @Override
    public int updateBookQuantity(int quantity, long id) {
        validationsUtil.validateBookQuantity(quantity);
        Optional<Book> byId = getBookById(id);
        Book book = byId.get();
        book.setQuantity(book.getQuantity() + quantity);
        return bookRepository.save(book).getQuantity();
    }

    @Override
    public boolean bookPurchased(long id, int quantity) {
        Optional<Book> bookById = getBookById(id);
        Book book = bookById.get();
        if (book.getQuantity() > quantity) {
            book.setQuantity(book.getQuantity() - quantity);
            bookRepository.save(book);
            return true;
        }
        return false;
    }

    private Optional<Book> getBookById(long id) {
        Optional<Book> byId = bookRepository.findById(id);
        if (byId.isEmpty()) {
            throw new BookValidationException(ErrorCode.BOOK_ID_INVALID.getCode(), ErrorCode.BOOK_ID_INVALID.getMessage());
        }
        return byId;
    }
}
