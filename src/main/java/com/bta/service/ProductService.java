package com.bta.service;

import com.bta.dto.CustomerDTO;
import com.bta.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    void create(ProductDTO product);

    ProductDTO update(ProductDTO product);

    List<ProductDTO> getAll();
}
