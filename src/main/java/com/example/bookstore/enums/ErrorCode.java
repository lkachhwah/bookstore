package com.example.bookstore.enums;

public enum ErrorCode {

    CUSTOMER_EMAIL_NULL_OR_EMPTY(40001, "EMAIL_NULL_OR_EMPTY"),
    CUSTOMER_EMAIL_ALREADY_USED(40002, "EMAIL_ALREADY_USED"),
    CUSTOMER_ID_NOT_VALID(40003, "CUSTOMER_ID_NOT_VALID"),
    CUSTOMER_DATA_NOT_VALID(40004,"CUSTOMER_DATA_NOT_VALID"),

    BOOK_NAME_INVALID(50001, "BOOK_NAME_INVALID"),
    BOOK_AUTHOR_NAME_INVALID(50002, "BOOK_AUTHOR_NAME_INVALID"),
    BOOK_QUANTITY_INVALID(50003, "BOOK_QUANTITY_INVALID"),
    BOOK_NAME_ALREADY_USED(50004, "BOOK_NAME_ALREADY_USED"),
    BOOK_ID_INVALID(50005, "BOOK_ID_INVALID"),
    BOOK_DATA_NOT_VALID(50006, "BOOK_DATA_NOT_VALID"),

    ORDER_ID_NOT_VALID(60001, "ORDER_ID_NOT_VALID"),
    ORDER_DATE_RANGE_NOT_VALID(60002, "ORDER_DATE_RANGE_NOT_VALID");

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
