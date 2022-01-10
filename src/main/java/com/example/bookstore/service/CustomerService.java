package com.example.bookstore.service;

import com.example.bookstore.dao.Customer;


public interface CustomerService {
    public Customer addCustomer(Customer customer);
    public Customer getCustomerDetailsById(long id) ;
}
