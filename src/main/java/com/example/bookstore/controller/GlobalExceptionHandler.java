package com.example.bookstore.controller;

import com.example.bookstore.exception.BookValidationException;
import com.example.bookstore.exception.CustomerValidationException;
import com.example.bookstore.exception.OrderDetailsException;
import com.example.bookstore.model.ResponseDTO;
import com.example.bookstore.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice
@Slf4j
@RestController
public class GlobalExceptionHandler {
    @Autowired
    private ResponseUtil responseUtil;
    private static final String  GOT="Got {}";

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResponseDTO exception(Exception e) {
        log.error("Got unexpected exception : {}" , e.getMessage(), e);
        return responseUtil.exception(1000);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(value = {CustomerValidationException.class})
    public ResponseDTO handleCustomerValidationException(CustomerValidationException e) {
        log.error(GOT , e.getMessage());
        return responseUtil.exception(e.getCode());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(value = {BookValidationException.class})
    public ResponseDTO handleBookValidationException(BookValidationException e) {
        log.error(GOT , e.getMessage());
        return responseUtil.exception(e.getCode());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(value = {OrderDetailsException.class})
    public ResponseDTO handleOrderDetailsException(OrderDetailsException e) {
        log.error(GOT , e.getMessage());
        return responseUtil.exception(e.getCode());
    }
}
