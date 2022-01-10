package com.example.bookstore.repository;

import com.example.bookstore.dao.OrderDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends MongoRepository<OrderDetails, Long> {

    Page<OrderDetails> findByCustomerId(long customerId, Pageable pageable);

    List<OrderDetails> findAllByOrderDateBetween(Date start,Date end);
}
