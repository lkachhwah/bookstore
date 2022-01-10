package com.example.bookstore.service.impl;

import com.example.bookstore.DataHelper;
import com.example.bookstore.dao.Customer;
import com.example.bookstore.dao.OrderDetails;
import com.example.bookstore.enums.OrderStatus;
import com.example.bookstore.exception.OrderDetailsException;
import com.example.bookstore.model.StatsDateDetails;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.CustomerService;
import com.example.bookstore.service.NextSequenceService;
import com.example.bookstore.utils.ValidationsUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @InjectMocks
    OrderServiceImpl orderService;

    @Mock
    ValidationsUtil validationsUtil;

    @Mock
    OrderRepository orderRepository;

    @Mock
    BookService bookService;

    @Mock
    CustomerService customerService;

    @Mock
    NextSequenceService nextSequenceService;

    @Before
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(orderService, "validationsUtil", new ValidationsUtil());
    }

    @Test
    public void addOrderSuccess() {
        OrderDetails orderDetails = DataHelper.getOrderDetails();
        Mockito.when(nextSequenceService.getNextSequence("order")).thenReturn(1);
        Mockito.when(bookService.bookPurchased(1, 2)).thenReturn(true);
        Mockito.when(orderRepository.save(Mockito.any(OrderDetails.class))).thenReturn(orderDetails);
        orderService.addOrder(orderDetails);
        Assert.assertTrue(OrderStatus.COMPLETED.equals(orderDetails.getStatus()));
        Mockito.verify(nextSequenceService, Mockito.times(1)).getNextSequence("order");
        Mockito.verify(orderRepository, Mockito.times(1)).save(Mockito.any(OrderDetails.class));
    }

    @Test
    public void addOrderfailed() {
        OrderDetails orderDetails = DataHelper.getOrderDetails();
        Mockito.when(nextSequenceService.getNextSequence("order")).thenReturn(1);
        Mockito.when(bookService.bookPurchased(1, 2)).thenReturn(false);
        Mockito.when(orderRepository.save(Mockito.any(OrderDetails.class))).thenReturn(orderDetails);
        orderService.addOrder(orderDetails);
        Assert.assertTrue(OrderStatus.FAILED.equals(orderDetails.getStatus()));
        Mockito.verify(nextSequenceService, Mockito.times(1)).getNextSequence("order");
        Mockito.verify(orderRepository, Mockito.times(1)).save(Mockito.any(OrderDetails.class));
    }

    @Test
    public void getOrderByIdSuccess() {
        Mockito.when(orderRepository.findById(1l)).thenReturn(Optional.of(DataHelper.getOrderDetails()));
        OrderDetails orderById = orderService.getOrderById(1l);
        Assert.assertTrue(Objects.nonNull(orderById));
    }

    @Test(expected = OrderDetailsException.class)
    public void getOrderByIdFailed() {
        Mockito.when(orderRepository.findById(1l)).thenReturn(Optional.empty());
        OrderDetails orderById = orderService.getOrderById(1l);
    }


    @Test
    public void getOrdersByCustomerId() {
        Page<OrderDetails> orderDetails = DataHelper.getPageOrderDetails();
        Mockito.when(orderRepository.findByCustomerId(Mockito.anyLong(), Mockito.any(Pageable.class)))
                .thenReturn(orderDetails);

        Mockito.when(customerService.getCustomerDetailsById(1l)).thenReturn(new Customer());
        Pageable paging = PageRequest.of(1, 3);
        Page<OrderDetails> ordersByCustomerId = orderService.getOrdersByCustomerId(1l, paging);
        Assert.assertTrue(Objects.nonNull(ordersByCustomerId));
    }

    @Test
    public void getOrderDetailsBetweenDateRangeSuccess() {

        Date startDate = new Date();
        Date endDate = getDateAfter8Days(startDate);
        StatsDateDetails statsDateDetails = new StatsDateDetails(startDate, endDate);
        Mockito.when(orderRepository.findAllByOrderDateBetween(statsDateDetails.getStartDate(), statsDateDetails.getEndDate()))
                .thenReturn(Arrays.asList(new OrderDetails()));
        List<OrderDetails> orderDetailsBetweenDateRange = orderService.getOrderDetailsBetweenDateRange(statsDateDetails);
        Assert.assertTrue(orderDetailsBetweenDateRange.size() == 1);
    }

    private Date getDateAfter8Days(Date startDate) {
        long l = startDate.getTime() + 8 * 24 * 60 * 60 * 1000;
        Date endDate = new Date(l);
        return endDate;
    }

    @Test(expected = OrderDetailsException.class)
    public void getOrderDetailsBetweenDateRangeInvalidRange() {
        Date endDate = new Date();
        Date startDate = getDateAfter8Days(endDate);
        StatsDateDetails statsDateDetails = new StatsDateDetails(startDate, endDate);
        orderService.getOrderDetailsBetweenDateRange(statsDateDetails);
    }
}