package com.example.bookstore.service.impl;

import com.example.bookstore.DataHelper;
import com.example.bookstore.dao.Customer;
import com.example.bookstore.exception.CustomerValidationException;
import com.example.bookstore.repository.CustomerRepository;
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
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    @InjectMocks
    CustomerServiceImpl customerService;
    @Mock
    CustomerRepository customerRepository;
    @Mock
    NextSequenceServiceImpl nextSequenceService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(customerService, "validationsUtil", new ValidationsUtil());
    }

    @Test
    public void addCustomerSuccess() {
        Customer customer = DataHelper.getCustomer();
        Mockito.when(customerRepository.findByEmailId(Mockito.anyString())).thenReturn(null);
        Mockito.when(nextSequenceService.getNextSequence("customer")).thenReturn(1);
        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
        Assert.assertNotNull(customerService.addCustomer(customer));
        Mockito.verify(customerRepository, Mockito.times(1)).save(Mockito.any(Customer.class));
    }

    @Test(expected = CustomerValidationException.class)
    public void addCustomerFailed_Email_isnull() {
        Customer customer = DataHelper.getCustomer();
        customer.setEmailId(null);
        customerService.addCustomer(customer);
    }

    @Test(expected = CustomerValidationException.class)
    public void addCustomerFailed_customer_isnull() {
        customerService.addCustomer(null);
    }

    @Test(expected = CustomerValidationException.class)
    public void addCustomerFailed_email_already_registered() {
        Customer customer = DataHelper.getCustomer();
        Mockito.when(customerRepository.findByEmailId(Mockito.anyString())).thenReturn(customer);
        customerService.addCustomer(customer);
    }

    @Test
    public void getCustomerDetailsByIdSuccess() {
        Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(DataHelper.getCustomer()));
        Assert.assertNotNull(customerService.getCustomerDetailsById(1));
    }

    @Test(expected = CustomerValidationException.class)
    public void getCustomerDetailsById_no_data() {
        Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        customerService.getCustomerDetailsById(1);
    }
}