package com.example.bookstore.controller;

import com.example.bookstore.dao.OrderDetails;
import com.example.bookstore.model.OrderDetailsDto;
import com.example.bookstore.model.ResponseDTO;
import com.example.bookstore.service.OrderService;
import com.example.bookstore.utils.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/order")
@Api(value = "Order management Service")
public class OrderController {


    @Autowired
    OrderService orderService;

    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    ObjectMapper objectMapper;

    @ApiOperation(value = "Add order", response = ResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO addOrderDetails(@RequestBody OrderDetailsDto orderDetailsDto)
    {
        return responseUtil.prepareSuccessResponse(orderService.addOrder(objectMapper.convertValue(orderDetailsDto,OrderDetails.class)),0,"en");
    }

    @ApiOperation(value = "Get order by order Id", response = ResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
    })
    @GetMapping(value ="/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO getOrderById(
            @ApiParam(name = "id", type = "long", value = "order Id", required = true)
            @PathVariable long id)
    {
        return responseUtil.prepareSuccessResponse(orderService.getOrderById(id),0,"en");
    }

    @ApiOperation(value = "Get all order by customer Id", response = ResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
    })

    @GetMapping(value ="/customer/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO getOrderByCustomerId(
            @ApiParam(name = "id", type = "long", value = "customer Id", required = true)
            @PathVariable long id,
            @ApiParam(name = "page", type = "int", value = "book Id", required = false)
            @RequestHeader(defaultValue = "0") int page,
            @ApiParam(name = "size", type = "int", value = "book Id", required = false)
            @RequestHeader(defaultValue = "3") int size)
    {
        Pageable paging = PageRequest.of(page, size);
        return responseUtil.prepareSuccessResponse(orderService.getOrdersByCustomerId(id,paging),0,"en");
    }

}
