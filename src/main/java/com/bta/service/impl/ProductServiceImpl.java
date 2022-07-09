package com.bta.service.impl;

import com.bta.dto.ProductDTO;
import com.bta.mapper.WebMapper;
import com.bta.model.Product;
import com.bta.repository.ProductRepository;
import com.bta.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private WebMapper<ProductDTO, Product> webMapper;


    @Override
    public void create(ProductDTO product) {
        repository.save(webMapper.toEntity(product));
    }

    @Override
    @Transactional
    public ProductDTO update(ProductDTO product) {

        final Integer skuCode = product.getSkuCode();
        final Product productFromDb = repository.findBySkuCode(skuCode);


        if (productFromDb == null) {
            String message = "Product with Sku Code = " + skuCode + "does not exist.";
            log.warn(message);
            throw new RuntimeException(message);
        }
        productFromDb.setUnitPrice(product.getUnitPrice());
        return webMapper.toDTO(productFromDb);
    }

    @Override
    public List<ProductDTO> getAll() {
        log.debug("Debug");
        return webMapper.toDtos(repository.findAll());
    }

    public void delete(ProductDTO product){
        repository.delete(webMapper.toEntity(product));
    }
}
