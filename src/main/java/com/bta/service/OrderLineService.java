package com.bta.service;

import com.bta.dto.OrderLineDTO;
import com.bta.dto.ProductDTO;

import java.util.List;

public interface OrderLineService {

    void create(OrderLineDTO orderLine);

    OrderLineDTO update(OrderLineDTO orderLine);

    List<OrderLineDTO> getAll();
}
