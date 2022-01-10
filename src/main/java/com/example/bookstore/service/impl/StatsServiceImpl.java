package com.example.bookstore.service.impl;

import com.example.bookstore.dao.OrderDetails;
import com.example.bookstore.model.StatsDateDetails;
import com.example.bookstore.model.StatsDetail;
import com.example.bookstore.model.StatsDetails;
import com.example.bookstore.service.OrderService;
import com.example.bookstore.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    OrderService orderService;

    @Override
    public StatsDetails getOrderDetailsBetweenDateRange(StatsDateDetails dateRange) {
        StatsDetails statsDetails=new StatsDetails();
        List<OrderDetails> orderList = orderService.getOrderDetailsBetweenDateRange(dateRange);
        if(CollectionUtils.isEmpty(orderList))
        {
                return statsDetails;
        }
        statsDetails.getStatsDetailList().addAll(processOrderList(orderList));
        return statsDetails;
    }

    private List<StatsDetail> processOrderList(List<OrderDetails> orderList) {

        List<StatsDetail> statsDetailList= new ArrayList<>();
        orderList.stream()
                .collect(Collectors.groupingBy(element
                        -> element.getOrderDate().getMonth()))
                .entrySet()
                .stream()
                .forEach(integerListEntry ->{
                    statsDetailList.add(processMonthlyData(integerListEntry));
                });
        return statsDetailList;
    }

    private StatsDetail processMonthlyData(Map.Entry<Integer, List<OrderDetails>> integerListEntry) {
        BigDecimal totalAmount= BigDecimal.ZERO;
        AtomicLong amount=new AtomicLong(0);
        AtomicLong totalBook=new AtomicLong(0);
        long totalOrder=integerListEntry.getValue().size();
        integerListEntry.getValue().stream().forEach(
                orderDetails -> {
                    totalBook.set(totalBook.get()+orderDetails.getQuantity());
                    amount.set(amount.get()+orderDetails.getAmount().longValue());
                }
        );
        totalAmount=totalAmount.add(new BigDecimal(amount.get()));
        return getStatsDetail(integerListEntry, totalAmount, totalBook, totalOrder);
    }

    private StatsDetail getStatsDetail(Map.Entry<Integer, List<OrderDetails>> integerListEntry, BigDecimal totalAmount, AtomicLong totalBook, long totalOrder) {
        StatsDetail statsDetail= new StatsDetail();
        setMonth(integerListEntry,statsDetail);
        statsDetail.setTotalOrder(totalOrder);
        statsDetail.setTotalBook(totalBook.get());
        statsDetail.setTotalAmount(totalAmount);
        return statsDetail;
    }

    private void setMonth(Map.Entry<Integer, List<OrderDetails>> integerListEntry, StatsDetail statsDetail) {
        Date date= integerListEntry.getValue().get(0).getOrderDate();
        SimpleDateFormat simpleformat = new SimpleDateFormat("MMMM");
        statsDetail.setMonth(simpleformat.format(date));
    }
}
