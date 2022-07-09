package com.bta.mapper;

import com.bta.dto.CustomerOrderDTO;
import com.bta.exception.ResolvingExeption;
import com.bta.model.Customer;
import com.bta.model.CustomerOrder;
import com.bta.repository.CustomerRepository;
import com.bta.resolver.CustomerResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;


@Component
public class CustomerOrderWebMapper implements WebMapper<CustomerOrderDTO, CustomerOrder> {

private CustomerResolver customerResolver;
    @Override
    public CustomerOrderDTO toDTO(CustomerOrder entity) {
        return CustomerOrderDTO.builder()
                .orderNumber(entity.getOrderNumber())
                .build();
    }

    @Override
    public CustomerOrder toEntity(CustomerOrderDTO dto) {
        var customer = customerResolver.resolveByEmail(dto.getCustomer().getEmail());
        return CustomerOrder.builder()
                .orderNumber(dto.getOrderNumber())
                .customer(customer)
                .build();
    }

}
