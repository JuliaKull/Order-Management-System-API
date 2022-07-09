package com.bta.mapper;

import com.bta.dto.ProductDTO;
import com.bta.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductWebMapper implements WebMapper<ProductDTO,Product>{
    @Override
    public ProductDTO toDTO(Product entity) {
        return ProductDTO.builder()
                .name(entity.getName())
                .skuCode(entity.getSkuCode())
                .unitPrice(entity.getUnitPrice())
                .orderLineCount(entity.getOrderLines().size())
                .build();

    }

    @Override
    public Product toEntity(ProductDTO dto) {
        return Product.builder()
                .name(dto.getName())
                .skuCode(dto.getSkuCode())
                .unitPrice(dto.getUnitPrice())
                .build();
    }
}
