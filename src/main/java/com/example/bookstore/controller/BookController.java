package com.example.bookstore.controller;

import com.example.bookstore.dao.Book;
import com.example.bookstore.model.BookDto;
import com.example.bookstore.model.ResponseDTO;
import com.example.bookstore.service.BookService;
import com.example.bookstore.utils.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/book")
@Api(value = "Book management Service")
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    ObjectMapper objectMapper;


    @ApiOperation(value = "Add Book", response = ResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO addBook(@RequestBody BookDto bookDto)
    {
        return responseUtil.prepareSuccessResponse(bookService.addBook(objectMapper.convertValue(bookDto,Book.class)),0,"en");
    }

    @ApiOperation(value = "Update  Book Quantity", response = ResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    })
    @PatchMapping (produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO patchBookQuantity(
            @ApiParam(name = "id", type = "long", value = "book Id", required = true)
            @RequestHeader long id,
            @ApiParam(name = "quantity", type = "int", value = "book quantity to be added", required = true)
            @RequestHeader int quantity)
    {
        return responseUtil.prepareSuccessResponse(bookService.updateBookQuantity(quantity,id),0,"en");
    }

}
