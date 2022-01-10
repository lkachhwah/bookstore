package com.example.bookstore.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    @ApiModelProperty(
            value = "Name of the Book",
            required = true,
            name = "bookName",
            dataType = "String")
    private String bookName;
    @ApiModelProperty(
            value = "Quantity of the Book",
            required = true,
            name = "quantity",
            dataType = "integer")
    private int quantity ;
    @ApiModelProperty(
            value = "authorName of the Book",
            required = true,
            name = "authorName",
            dataType = "String")
    private String authorName;
    @ApiModelProperty(
            value = "price of the Book",
            required = true,
            name = "price",
            dataType = "BigDecimal")
    private BigDecimal price= BigDecimal.TEN;
}
