package com.bta.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bta.dto.CustomerDTO;
import com.bta.dto.CustomerOrderDTO;
import com.bta.dto.CustomerOrdersDTO;
import com.bta.mapper.WebMapper;
import com.bta.model.Customer;
import com.bta.model.CustomerOrder;
import com.bta.repository.CustomerOrderRepository;
import com.bta.repository.CustomerRepository;
import com.bta.repository.OrderLineRepository;
import com.bta.repository.ProductRepository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomerOrderServiceImpl.class})
@ExtendWith(MockitoExtension.class)
class CustomerOrderServiceImplTest {
    @Mock
    private CustomerOrderRepository customerOrderRepository;

    @InjectMocks
    private CustomerOrderServiceImpl customerOrderServiceImpl;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private WebMapper<CustomerOrderDTO, CustomerOrder> webMapper;


    @Test
    void testCreateCustomerOrder() {

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomer(new Customer());
        customerOrder.setOrderNumber("1");
        when(webMapper.toEntity((CustomerOrderDTO) any())).thenReturn(customerOrder);
        when(customerOrderRepository.save((CustomerOrder) any())).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> customerOrderServiceImpl.create(new CustomerOrderDTO()));
        verify(webMapper).toEntity((CustomerOrderDTO) any());
        verify(customerOrderRepository).save((CustomerOrder) any());
    }

    @Test
    void testCreateAllCustomerOrders() {
        Customer customer = new Customer();
        customer.setCustomerOrders(new ArrayList<>());
        customer.setEmail("test@test.com");
        customer.setFirstName("Firstname");
        customer.setId(1L);
        customer.setLastName("Lastname");
        customer.setRegistrationCode(BigInteger.valueOf(1L));
        customer.setTelephone("112333221");
        when(customerRepository.findByEmail((String) any())).thenReturn(customer);

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomer(customer);
        customerOrder.setId(1L);
        customerOrder.setOrderNumber("1");
        customerOrder.setSubmissionDate(null);
        when(customerOrderRepository.save((CustomerOrder) any())).thenReturn(customerOrder);
        CustomerDTO customerDTO = mock(CustomerDTO.class);
        when(customerDTO.getEmail()).thenReturn("test@test.com");
        CustomerDTO customer2 = mock(CustomerDTO.class);

        CustomerOrderDTO customerOrderDTO = new CustomerOrderDTO(customer2, "1", new ArrayList<>());
        customerOrderDTO.setCustomer(customerDTO);

        ArrayList<CustomerOrderDTO> customerOrderDTOList = new ArrayList<>();
        customerOrderDTOList.add(customerOrderDTO);
        CustomerOrdersDTO customerOrdersDTO = mock(CustomerOrdersDTO.class);
        when(customerOrdersDTO.getCustomerOrders()).thenReturn(customerOrderDTOList);
        customerOrderServiceImpl.createAll(customerOrdersDTO);
        verify(customerRepository).findByEmail((String) any());
        verify(customerOrderRepository).save((CustomerOrder) any());
        verify(customerOrdersDTO).getCustomerOrders();
        verify(customerDTO).getEmail();
    }



    @Test
    void testGetAll() {
        ArrayList<CustomerOrderDTO> customerOrderDTOList = new ArrayList<>();
        when(webMapper.toDtos((List<CustomerOrder>) any())).thenReturn(customerOrderDTOList);
        List<CustomerOrderDTO> actualAll = customerOrderServiceImpl.getAll();
        assertSame(customerOrderDTOList, actualAll);
        verify(webMapper).toDtos((List<CustomerOrder>) any());
        verify(customerOrderRepository).findAll();
    }

}

