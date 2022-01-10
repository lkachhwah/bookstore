package com.example.bookstore.service;

import com.example.bookstore.dao.OrderDetails;
import com.example.bookstore.model.StatsDateDetails;
import com.example.bookstore.model.StatsDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    public OrderDetails addOrder(OrderDetails orderDetails);

    public OrderDetails  getOrderById(long id);

    public Page<OrderDetails> getOrdersByCustomerId(long id, Pageable pageable);

    public List<OrderDetails> getOrderDetailsBetweenDateRange(StatsDateDetails dateRange);
}
