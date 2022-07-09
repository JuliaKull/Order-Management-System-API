package com.bta.dto;

import com.bta.model.CustomerOrder;
import com.bta.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineDTO {

    private ProductDTO product;

    private Integer quantity;

}
