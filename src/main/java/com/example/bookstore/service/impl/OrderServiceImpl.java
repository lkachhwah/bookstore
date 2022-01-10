package com.example.bookstore.service.impl;

import com.example.bookstore.dao.OrderDetails;
import com.example.bookstore.enums.ErrorCode;
import com.example.bookstore.enums.OrderStatus;
import com.example.bookstore.exception.OrderDetailsException;
import com.example.bookstore.model.StatsDateDetails;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.CustomerService;
import com.example.bookstore.service.NextSequenceService;
import com.example.bookstore.service.OrderService;
import com.example.bookstore.utils.ValidationsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ValidationsUtil validationsUtil;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    BookService bookService;

    @Autowired
    CustomerService customerService;

    @Autowired
    NextSequenceService nextSequenceService;

    @Override
    public OrderDetails addOrder(OrderDetails orderDetails) {
        validationsUtil.validateOrder(orderDetails);
        orderDetails.setOrderDate(new Date());
        validateCustomerId(orderDetails.getCustomerId());
        orderDetails.setStatus(OrderStatus.FAILED);
        orderDetails.setId(nextSequenceService.getNextSequence("order"));
        if (bookService.bookPurchased(orderDetails.getBookId(), orderDetails.getQuantity())) {
            orderDetails.setStatus(OrderStatus.COMPLETED);
        }
        return orderRepository.save(orderDetails);
    }

    private void validateCustomerId(long customerId) {
        customerService.getCustomerDetailsById(customerId);
    }

    @Override
    public OrderDetails getOrderById(long id) {
        Optional<OrderDetails> byId = orderRepository.findById(id);
        if (byId.isEmpty()) {
            throw new OrderDetailsException(ErrorCode.ORDER_ID_NOT_VALID.getCode(), ErrorCode.ORDER_ID_NOT_VALID.getMessage());
        }
        return byId.get();
    }

    @Override
    public Page<OrderDetails> getOrdersByCustomerId(long id, Pageable pageable) {
        validateCustomerId(id);
        return orderRepository.findByCustomerId(id, pageable);
    }

    @Override
    public List<OrderDetails> getOrderDetailsBetweenDateRange(StatsDateDetails dateRange) {
        validationsUtil.validteDateRange(dateRange);
        return orderRepository.findAllByOrderDateBetween(dateRange.getStartDate(), dateRange.getEndDate());
    }
}
