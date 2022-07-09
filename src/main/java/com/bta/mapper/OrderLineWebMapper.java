package com.bta.mapper;

import com.bta.dto.CustomerDTO;
import com.bta.dto.OrderLineDTO;
import com.bta.model.Customer;
import com.bta.model.OrderLine;
import org.springframework.stereotype.Component;

@Component
public class OrderLineWebMapper implements WebMapper <OrderLineDTO, OrderLine>{
    @Override
    public OrderLineDTO toDTO(OrderLine entity) {
        return OrderLineDTO.builder()
                .quantity(entity.getQuantity())
                .build();

    }

    @Override
    public OrderLine toEntity(OrderLineDTO dto) {
        return OrderLine.builder()
                .quantity(dto.getQuantity())
                .build();
    }
}
