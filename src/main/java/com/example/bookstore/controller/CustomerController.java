package com.example.bookstore.controller;

import com.example.bookstore.dao.Customer;
import com.example.bookstore.model.CustomerDto;
import com.example.bookstore.model.ResponseDTO;
import com.example.bookstore.service.CustomerService;
import com.example.bookstore.utils.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/customer")
@Api(value = "Customer managementService Service")
public class CustomerController {

    @Autowired
    ResponseUtil responseUtill;

    @Autowired
    CustomerService customerService;

    @Autowired
    ObjectMapper objectMapper;

    @ApiOperation(value = "Add Customer", response = ResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO addCustomer(@RequestBody CustomerDto customerDto)
    {
        return responseUtill.prepareSuccessResponse(customerService.addCustomer(objectMapper.convertValue(customerDto,Customer.class)),0,"en");
    }

    @ApiOperation(value = "Get Customer details by customer Id", response = ResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    })
    @GetMapping (value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseDTO getCustomerById(
            @ApiParam(name = "id", type = "long", value = "Customer Id", required = true)
            @PathVariable long id) {
        return responseUtill.prepareSuccessResponse(customerService.getCustomerDetailsById(id),0,"en");
    }

}
