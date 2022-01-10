package com.example.bookstore.service.impl;

import com.example.bookstore.DataHelper;
import com.example.bookstore.dao.Book;
import com.example.bookstore.exception.BookValidationException;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.service.NextSequenceService;
import com.example.bookstore.utils.ValidationsUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

    @InjectMocks
    BookServiceImpl bookService;

    @Mock
    BookRepository bookRepository;

    @Mock
    NextSequenceService nextSequenceService;

    @Mock
    ValidationsUtil validationsUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(bookService, "validationsUtil", new ValidationsUtil());
    }

    @Test
    public void addBookSuccess() {
        Book book = DataHelper.getBook();
        Mockito.when(bookRepository.findByBookName(Mockito.anyString())).thenReturn(null);
        Mockito.when(nextSequenceService.getNextSequence("book")).thenReturn(1);
        Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);
        Book book1 = bookService.addBook(book);
        Assert.assertNotNull(book1);
        Mockito.verify(bookRepository, Mockito.times(1)).findByBookName(Mockito.anyString());
    }

    @Test(expected = BookValidationException.class)
    public void addBookFailedInvalidBookOject() {
        bookService.addBook(null);
    }

    @Test(expected = BookValidationException.class)
    public void addBookFailed_empty_authorName() {
        Book book = DataHelper.getBook();
        book.setAuthorName(null);
        bookService.addBook(book);
    }

    @Test(expected = BookValidationException.class)
    public void addBookFailed_empty_bookName() {
        bookService.addBook(null);
    }


    @Test
    public void updateBookQuantitySuccess() {

        Book book = DataHelper.getBook();
        Mockito.when(bookRepository.findById(1l)).thenReturn(Optional.of(book));
        book.setQuantity(3);
        Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);
        int bookCount = bookService.updateBookQuantity(2, 1);
        Assert.assertTrue(bookCount > 2);
    }

    @Test(expected = BookValidationException.class)
    public void updateBookQuantityFailed_quantity_invalid() {
        bookService.updateBookQuantity(-1, 1);
    }

    @Test(expected = BookValidationException.class)
    public void updateBookQuantityFailed_bookId_invalid() {
        bookService.updateBookQuantity(-1, 1);
    }

    @Test
    public void bookPurchasedSuccess() {
        Book book = DataHelper.getBook();
        book.setQuantity(5);
        Mockito.when(bookRepository.findById(1l)).thenReturn(Optional.of(book));
        Assert.assertTrue(bookService.bookPurchased(1, 1));
    }

    @Test
    public void bookPurchased_failed_asStock_isLess_thanOrder() {
        Book book = DataHelper.getBook();
        book.setQuantity(5);
        Mockito.when(bookRepository.findById(1l)).thenReturn(Optional.of(book));
        Assert.assertFalse(bookService.bookPurchased(1, 10));
    }

}