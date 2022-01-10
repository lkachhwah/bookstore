package com.example.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StatsDetail {
    private String month;
    private long totalOrder;
    private long totalBook;
    private BigDecimal totalAmount;
}
