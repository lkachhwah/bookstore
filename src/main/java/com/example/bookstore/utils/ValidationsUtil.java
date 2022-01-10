package com.example.bookstore.utils;

import com.example.bookstore.dao.Book;
import com.example.bookstore.dao.Customer;
import com.example.bookstore.dao.OrderDetails;
import com.example.bookstore.enums.ErrorCode;
import com.example.bookstore.exception.BookValidationException;
import com.example.bookstore.exception.CustomerValidationException;
import com.example.bookstore.exception.OrderDetailsException;
import com.example.bookstore.model.StatsDateDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Objects;

@Component
public class ValidationsUtil {
    public void validateCustomer(Customer customer) {
        if (Objects.isNull(customer)) {
            throw new CustomerValidationException(ErrorCode.CUSTOMER_EMAIL_NULL_OR_EMPTY.getCode(), ErrorCode.CUSTOMER_EMAIL_NULL_OR_EMPTY.getMessage());
        }
        if (!StringUtils.hasLength(customer.getEmailId())) {
            throw new CustomerValidationException(ErrorCode.CUSTOMER_EMAIL_NULL_OR_EMPTY.getCode(), ErrorCode.CUSTOMER_EMAIL_NULL_OR_EMPTY.getMessage());
        }
    }

    public void validateBook(Book book) {
        if (Objects.isNull(book)) {
            throw new BookValidationException(ErrorCode.BOOK_DATA_NOT_VALID.getCode(), ErrorCode.BOOK_DATA_NOT_VALID.getMessage());
        }
        if (!StringUtils.hasLength(book.getBookName())) {
            throw new BookValidationException(ErrorCode.BOOK_NAME_INVALID.getCode(), ErrorCode.BOOK_NAME_INVALID.getMessage());
        }
        if (!StringUtils.hasLength(book.getAuthorName())) {
            throw new BookValidationException(ErrorCode.BOOK_AUTHOR_NAME_INVALID.getCode(), ErrorCode.BOOK_AUTHOR_NAME_INVALID.getMessage());
        }
        validateBookQuantity(book.getQuantity());
    }

    public void validateBookQuantity(int quantity) {
        {
            if (quantity<=0 || quantity>1000 ) {
                throw new BookValidationException(ErrorCode.BOOK_QUANTITY_INVALID.getCode(), ErrorCode.BOOK_QUANTITY_INVALID.getMessage());
            }
        }
    }

    public void validateOrder(OrderDetails orderDetails) {
        validateBookQuantity(orderDetails.getQuantity());
    }

    public void validteDateRange(StatsDateDetails dateRange) {
        if(Objects.isNull(dateRange.getStartDate()))
        {dateRange.setStartDate(new Date());}
        if(Objects.isNull(dateRange.getEndDate()))
        {dateRange.setEndDate(new Date());}

        if(! dateRange.getStartDate().before(dateRange.getEndDate()))
        {
            throw new OrderDetailsException(ErrorCode.ORDER_DATE_RANGE_NOT_VALID.getCode(),ErrorCode.ORDER_DATE_RANGE_NOT_VALID.getMessage());
        }
    }
}
