package com.bta.mapper;

import com.bta.dto.CustomerDTO;
import com.bta.model.Customer;
import lombok.experimental.UtilityClass;

import java.math.BigInteger;

@UtilityClass
class CustomerWebMapperHelper {

    static Customer getCustomer(){
       return Customer.builder()
                .telephone("+3727778899")
                .email("test@test.com")
                .registrationCode(BigInteger.valueOf(300l))
                .firstName("FirstName")
                .lastName("LastName")
                .build();
    }

    static CustomerDTO getCustomerDto(){
        return CustomerDTO.builder()
                .telephone("+3727778899")
                .email("test@test.com")
                .registrationCode(BigInteger.valueOf(300l))
                .firstName("FirstName")
                .lastName("LastName")
                .build();
    }
}
