package com.bta.controller;


import com.bta.dto.CustomerDTO;
import com.bta.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    public List<CustomerDTO> getAll(){
       return customerService.getAll();

    }

    @PostMapping("/create")
    public ResponseEntity<CustomerDTO> create (@RequestBody CustomerDTO customer){
        customerService.create(customer);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCustomer(@RequestParam String email){
        customerService.delete(email);
        return ResponseEntity.ok(email);
    }

    @PutMapping("/update")
    public ResponseEntity<CustomerDTO> update(@RequestBody CustomerDTO customer) {
        final CustomerDTO updatedCustomer = customerService.update(customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

}
