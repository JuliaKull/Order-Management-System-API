package com.bta.controller;

import com.bta.dto.CustomerDTO;
import com.bta.dto.ProductDTO;
import com.bta.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
private ProductService productService;


    @GetMapping("/all")
    public List<ProductDTO> all(){
        return productService.getAll();
    }

    @PostMapping("/create")
    public  ResponseEntity<ProductDTO> create(@RequestBody ProductDTO product){
        productService.create(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO product){
        final ProductDTO updatedProduct = productService.update(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
