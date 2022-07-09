package com.bta.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bta.dto.ProductDTO;
import com.bta.mapper.WebMapper;
import com.bta.model.OrderLine;
import com.bta.model.Product;
import com.bta.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductServiceImpl.class})
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @Mock
    private WebMapper<ProductDTO, Product> webMapper;

    @Test
    void testCreateProduct() {
        Product product = new Product();
        when(webMapper.toEntity((ProductDTO) any())).thenReturn(product);
        productServiceImpl.create(new ProductDTO());
        verify(productRepository).save((Product) any());
    }

    @Test
    void testUpdateProduct() {
        Product product = mock(Product.class);
        product.setSkuCode(1);
        when(productRepository.findBySkuCode((Integer) any())).thenReturn(product);
        when(webMapper.toDTO((Product) any())).thenReturn(new ProductDTO());
        productServiceImpl.update(new ProductDTO());
    }


    @Test
    void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(new ArrayList<>());
        ArrayList<ProductDTO> productDTOList = new ArrayList<>();
        when(webMapper.toDtos((List<Product>) any())).thenReturn(productDTOList);
        List<ProductDTO> actualAll = productServiceImpl.getAll();
        assertSame(productDTOList, actualAll);
        verify(productRepository).findAll();
        verify(webMapper).toDtos((List<Product>) any());
    }


    @Test
    void testDeleteProduct() {
        Product product = new Product();
        when(webMapper.toEntity((ProductDTO) any())).thenReturn(product);
        productServiceImpl.delete(new ProductDTO());
        verify(productRepository).delete((Product) any());
    }

}

