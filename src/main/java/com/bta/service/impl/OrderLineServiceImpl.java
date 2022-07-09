package com.bta.service.impl;

import com.bta.dto.CustomerDTO;
import com.bta.dto.OrderLineDTO;
import com.bta.dto.ProductDTO;
import com.bta.mapper.WebMapper;
import com.bta.model.Customer;
import com.bta.model.OrderLine;
import com.bta.repository.OrderLineRepository;
import com.bta.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderLineServiceImpl implements OrderLineService {

    @Autowired
    private WebMapper<OrderLineDTO, OrderLine> webMapper;

    @Autowired
    private OrderLineRepository orderLineRepository;


    @Override
    public void create(OrderLineDTO orderLine) {
        final OrderLine entity = webMapper.toEntity(orderLine);
        orderLineRepository.save(entity);
    }

    @Override
    public OrderLineDTO update(OrderLineDTO orderLine) {
        return null;
    }

    @Override
    public List<OrderLineDTO> getAll() {
        return webMapper.toDtos(orderLineRepository.findAll());
    }
}
