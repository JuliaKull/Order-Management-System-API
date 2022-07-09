package com.bta.service.impl;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bta.dto.OrderLineDTO;
import com.bta.mapper.WebMapper;
import com.bta.model.Customer;
import com.bta.model.CustomerOrder;
import com.bta.model.OrderLine;
import com.bta.model.Product;
import com.bta.repository.OrderLineRepository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OrderLineServiceImpl.class})
@ExtendWith(MockitoExtension.class)
class OrderLineServiceImplTest {
    @Mock
    private OrderLineRepository orderLineRepository;

    @InjectMocks
    private OrderLineServiceImpl orderLineServiceImpl;

    @Mock
    private WebMapper<OrderLineDTO, OrderLine> webMapper;


    @Test
    void testCreateOrderLine() {
        Customer customer = new Customer();
        CustomerOrder order = new CustomerOrder();
        order.setCustomer(customer);
        Product product = new Product();
        OrderLine orderLine = new OrderLine();
        orderLine.setCustomerOrder(order);
        orderLine.setProduct(product);
        orderLine.setQuantity(1);
        when(webMapper.toEntity((OrderLineDTO) any())).thenReturn(orderLine);
        orderLineServiceImpl.create(new OrderLineDTO());
        verify(orderLineRepository).save((OrderLine) any());
        verify(webMapper).toEntity((OrderLineDTO) any());
        assertTrue(orderLineServiceImpl.getAll().isEmpty());
    }

    @Test
    void testUpdateOrderLine() {
        assertNull(orderLineServiceImpl.update(new OrderLineDTO()));
    }

    @Test
    void testGetAllOrdersLine() {
        when(orderLineRepository.findAll()).thenReturn(new ArrayList<>());
        ArrayList<OrderLineDTO> orderLineDTOList = new ArrayList<>();
        when(webMapper.toDtos((List<OrderLine>) any())).thenReturn(orderLineDTOList);
        List<OrderLineDTO> actualAll = orderLineServiceImpl.getAll();
        assertSame(orderLineDTOList, actualAll);
        verify(orderLineRepository).findAll();
        verify(webMapper).toDtos((List<OrderLine>) any());
    }
}

