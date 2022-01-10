package com.example.bookstore.repository;

import com.example.bookstore.dao.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer,Long> {

    public Customer findByEmailId(String emailId);
}
