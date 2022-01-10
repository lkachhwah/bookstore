package com.example.bookstore.service;

import com.example.bookstore.model.StatsDateDetails;
import com.example.bookstore.model.StatsDetails;

public interface StatsService {
    StatsDetails getOrderDetailsBetweenDateRange(StatsDateDetails dateRange);
}
