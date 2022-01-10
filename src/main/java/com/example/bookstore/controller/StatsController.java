package com.example.bookstore.controller;

import com.example.bookstore.model.ResponseDTO;
import com.example.bookstore.model.StatsDateDetails;
import com.example.bookstore.service.StatsService;
import com.example.bookstore.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/stats")
@Api(value = "Statistic Service")
public class StatsController {
    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    StatsService statsService;

    @ApiOperation(value = "Get the Stats", response = ResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO getOrderDetailsBetweenDateRange(@RequestBody StatsDateDetails dateRange)
    {
        return responseUtil.prepareSuccessResponse(statsService.getOrderDetailsBetweenDateRange(dateRange),0,"en");
    }
}
