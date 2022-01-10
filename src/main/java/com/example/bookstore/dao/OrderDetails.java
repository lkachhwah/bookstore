package com.example.bookstore.dao;

import com.example.bookstore.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetails {
    @Id
    private long id;
    private long customerId;
    private Date orderDate;
    private long bookId;
    private int quantity;
    private OrderStatus status;
    private BigDecimal amount;
}
