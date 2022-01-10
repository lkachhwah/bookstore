package com.example.bookstore.service.impl;

import com.example.bookstore.service.OrderService;
import com.example.bookstore.utils.ValidationsUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class StatsServiceImplTest {

    @InjectMocks
    StatsServiceImpl  statsService;

    @Mock
    OrderService orderService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getOrderDetailsBetweenDateRange() {
    }
}