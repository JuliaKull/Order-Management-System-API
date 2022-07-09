package com.bta.service;

import com.bta.dto.CustomerOrderDTO;
import com.bta.dto.CustomerOrdersDTO;

import java.util.List;

public interface CustomerOrderService {

    void create(CustomerOrderDTO customerOrder);

    void createAll(CustomerOrdersDTO customerOrderDtos);
    List<CustomerOrderDTO> getAll();

}
