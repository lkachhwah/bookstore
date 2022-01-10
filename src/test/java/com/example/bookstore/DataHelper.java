package com.example.bookstore;

import com.example.bookstore.dao.Book;
import com.example.bookstore.dao.Customer;
import com.example.bookstore.dao.OrderDetails;
import com.example.bookstore.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class DataHelper {

    public static OrderDetails getOrderDetails()
    {
        return OrderDetails.builder().orderDate(new Date()).amount(new BigDecimal(20.25))
                .bookId(1).customerId(1)
                .quantity(2)
                .status(OrderStatus.COMPLETED).build();
    }

    public static Page<OrderDetails>  getPageOrderDetails(){
         return new Page<OrderDetails>() {
            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public long getTotalElements() {
                return 0;
            }

            @Override
            public <U> Page<U> map(Function<? super OrderDetails, ? extends U> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public int getNumberOfElements() {
                return 0;
            }

            @Override
            public List<OrderDetails> getContent() {
                return null;
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator<OrderDetails> iterator() {
                return null;
            }
        };
    }

    public static Book getBook()
    {
        return  Book.builder().bookName("testBook").authorName("testAuthor").quantity(1).price(BigDecimal.TEN)
                .build();
    }

    public static Customer getCustomer()
    {
        return Customer.builder().emailId("lalit@g.com").firstName("Fname")
                .lastName("lname").middleName("mname").build();
    }
}
