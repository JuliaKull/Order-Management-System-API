package com.bta.controller;

import com.bta.dto.CustomerOrderDTO;
import com.bta.dto.CustomerOrdersDTO;
import com.bta.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/customer-order")
@RestController
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @PostMapping("/create")
    public ResponseEntity<?>create(@Valid @RequestBody CustomerOrderDTO customerOrder){
        customerOrderService.create(customerOrder);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/create-all")
    public ResponseEntity<?>createAll(@RequestBody CustomerOrdersDTO customerOrderDtos){
        customerOrderService.createAll(customerOrderDtos);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<CustomerOrderDTO> getAll() {
        return customerOrderService.getAll();
    }


}
