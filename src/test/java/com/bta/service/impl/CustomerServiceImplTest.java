package com.bta.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bta.dto.CustomerDTO;
import com.bta.dto.ProductDTO;
import com.bta.mapper.WebMapper;
import com.bta.model.Customer;
import com.bta.model.Product;
import com.bta.repository.CustomerRepository;

import java.math.BigInteger;
import java.util.ArrayList;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomerServiceImpl.class})
@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Mock
    private WebMapper<CustomerDTO, Customer> webMapper;


    @Test
    void createCustomer() {
        Customer customer = new Customer();
        when(customerRepository.save((Customer) any())).thenReturn(customer);
        when(webMapper.toEntity((CustomerDTO) any())).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> customerServiceImpl.create(mock(CustomerDTO.class)));
        verify(webMapper).toEntity((CustomerDTO) any());
    }

    @Test
    void testUpdateCustomer() {
        Customer customer = new Customer();
        customer.setCustomerOrders(new ArrayList<>());
        customer.setEmail("test@test.com");
        when(customerRepository.findByEmail((String) any())).thenReturn(customer);
        when(webMapper.toDTO((Customer) any())).thenReturn(mock(CustomerDTO.class));
        CustomerDTO customerDTO = mock(CustomerDTO.class);
        when(customerDTO.getEmail()).thenReturn("test@test.com");
        customerServiceImpl.update(customerDTO);
        verify(customerRepository).findByEmail((String) any());
        verify(webMapper).toDTO((Customer) any());
    }

    @Test
    void testUpdateCustomerIfEmailIsNull() {
        Customer customer = new Customer();
        customer.setCustomerOrders(new ArrayList<>());
        when(customerRepository.findByEmail((String) any())).thenReturn(customer);
        when(webMapper.toDTO((Customer) any())).thenReturn(mock(CustomerDTO.class));
        CustomerDTO customerDTO = mock(CustomerDTO.class);
        when(customerDTO.getEmail()).thenReturn(null);
        assertThrows(RuntimeException.class, () -> customerServiceImpl.update(customerDTO));
        verify(customerDTO).getEmail();
    }

    @Test
    void testUpdateCustomerIfEmailIsEmpty() {
        Customer customer = new Customer();
        customer.setCustomerOrders(new ArrayList<>());
        when(customerRepository.findByEmail((String) any())).thenReturn(customer);
        when(webMapper.toDTO((Customer) any())).thenReturn(mock(CustomerDTO.class));
        CustomerDTO customerDTO = mock(CustomerDTO.class);
        when(customerDTO.getEmail()).thenReturn("");
        assertThrows(RuntimeException.class, () -> customerServiceImpl.update(customerDTO));
        verify(customerDTO).getEmail();
    }

    @Test
    void testGetAllCustomers() {
        when(customerRepository.findAll()).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> customerServiceImpl.getAll());
        verify(customerRepository).findAll();
    }

    @Test
    void testDeleteCustomer() {
        Customer customer = new Customer();
        customer.setEmail("test@test.com");
        when(customerRepository.deleteByEmail((String) any())).thenReturn(customer);
        customerServiceImpl.delete("test@test.com");
        verify(customerRepository).deleteByEmail((String) any());
    }
}

