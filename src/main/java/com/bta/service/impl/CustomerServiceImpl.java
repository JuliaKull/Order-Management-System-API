package com.bta.service.impl;

import com.bta.dto.CustomerDTO;
import com.bta.mapper.WebMapper;
import com.bta.model.Customer;
import com.bta.repository.CustomerRepository;
import com.bta.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository repository;

    @Autowired
    private WebMapper<CustomerDTO, Customer> webMapper;

    @Override
    public void create(CustomerDTO customer) {
       final Customer entity = webMapper.toEntity(customer);
       repository.save(entity);
    }

    @Override
    public CustomerDTO update(CustomerDTO customer) {
        final String email = customer.getEmail();
        if(email == null || email.isEmpty()){
            throw new RuntimeException("Email must be not Null or Empty!");
        }
        Customer customerFromDb = repository.findByEmail(email);
        if (customerFromDb == null) {
            String message = "Customer with email = " + email + "does not exist!";
            log.warn(message);
            throw new RuntimeException(message);
        }
        customerFromDb.setFirstName(customer.getFirstName());
        customerFromDb.setLastName(customer.getLastName());
        customerFromDb.setTelephone(customerFromDb.getTelephone());
        return webMapper.toDTO(customerFromDb);
    }

    @Override
    public List<CustomerDTO> getAll(){
        return webMapper.toDtos((List<Customer>) repository.findAll());
    }

    @Transactional
    @Override
    public void delete(String email) {
        repository.deleteByEmail(email);
    }

}
