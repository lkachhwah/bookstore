package com.example.bookstore.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {
    @ApiModelProperty(
            value = "0 means Success and not zero value indicate processing fail",
            required = true,
            name = "code",
            dataType = "int")
    private int code;
    @ApiModelProperty(
            value = "Description about operation",
            required = true,
            name = "message",
            dataType = "String")
    private String message;
    @ApiModelProperty(
            value = "data",
            required = true,
            name = "data",
            dataType = "Object")
    private T data;
}
