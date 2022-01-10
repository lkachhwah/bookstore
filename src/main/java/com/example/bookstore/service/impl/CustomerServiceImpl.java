package com.example.bookstore.service.impl;

import com.example.bookstore.dao.Customer;
import com.example.bookstore.enums.ErrorCode;
import com.example.bookstore.exception.CustomerValidationException;
import com.example.bookstore.repository.CustomerRepository;
import com.example.bookstore.service.CustomerService;
import com.example.bookstore.utils.ValidationsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    NextSequenceServiceImpl nextSequenceService;
    @Autowired
    ValidationsUtil validationsUtil;


    @Override
    public Customer addCustomer(Customer customer) {
        validationsUtil.validateCustomer(customer);
        checkEmailAlreadyOccupied(customer);
        return saveCustomer(customer);
    }

    @Override
    public Customer getCustomerDetailsById(long id) {
        Optional<Customer> byId = customerRepository.findById(Long.valueOf(id));
        if(byId.isPresent())
        {
            return byId.get();
        }
            throw  new CustomerValidationException(ErrorCode.CUSTOMER_ID_NOT_VALID.getCode(), ErrorCode.CUSTOMER_ID_NOT_VALID.getMessage());
    }

    private Customer saveCustomer(Customer customer) {
        customer.setId(nextSequenceService.getNextSequence("customer"));
        return customerRepository.save(customer);
    }

    private void checkEmailAlreadyOccupied(Customer customer) {
        Customer byEmailId = customerRepository.findByEmailId(customer.getEmailId());
        if(Objects.nonNull(byEmailId))
        {
            throw new CustomerValidationException(ErrorCode.CUSTOMER_EMAIL_ALREADY_USED.getCode(), ErrorCode.CUSTOMER_EMAIL_ALREADY_USED.getMessage());
        }
    }
}
