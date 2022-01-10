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
public class OrderDetailsDto {
    @ApiModelProperty(
            value = "customerId of the customer",
            required = true,
            name = "customerId",
            dataType = "long")
    private long customerId;

    @ApiModelProperty(
            value = "bookId of the book",
            required = true,
            name = "bookId",
            dataType = "long")
    private long bookId;

    @ApiModelProperty(
            value = "quantity of book for order",
            required = true,
            name = "quantity",
            dataType = "int")
    private int quantity;

    @ApiModelProperty(
            value = "amount of the order",
            required = true,
            name = "amount",
            dataType = "BigDecimal")
    private BigDecimal amount;
}
