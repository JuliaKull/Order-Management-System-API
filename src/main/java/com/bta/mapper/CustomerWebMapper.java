package com.bta.mapper;

import com.bta.dto.CustomerDTO;
import com.bta.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerWebMapper implements WebMapper <CustomerDTO,Customer>{
    @Override
    public CustomerDTO toDTO(Customer entity) {
        if(entity==null){
            return null;
        }
        return CustomerDTO.builder()
                .email(entity.getEmail())
                .lastName(entity.getLastName())
                .firstName(entity.getFirstName())
                .telephone(entity.getTelephone())
                .registrationCode(entity.getRegistrationCode())
                .build();

    }

    @Override
    public Customer toEntity(CustomerDTO dto) {
        if(dto==null){
            return null;
        }
        return Customer.builder()
                .email(dto.getEmail())
                .lastName(dto.getLastName())
                .firstName(dto.getFirstName())
                .registrationCode(dto.getRegistrationCode())
                .telephone(dto.getTelephone())
                .build();
    }
}
