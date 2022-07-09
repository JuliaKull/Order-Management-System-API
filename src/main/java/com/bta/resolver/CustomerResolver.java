package com.bta.resolver;

import com.bta.exception.ResolvingExeption;
import com.bta.model.Customer;
import com.bta.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class CustomerResolver {


    @Autowired
    private CustomerRepository customerRepository;


    public Customer resolveByEmail(String email){

        final Customer customer = customerRepository.findByEmail(email);
        if(customer==null){
            final String message = "Customer with email:" + email + " does not exist.";
            log.warn(message);
            throw new ResolvingExeption(message);
        }
        return customer;
    }
}
