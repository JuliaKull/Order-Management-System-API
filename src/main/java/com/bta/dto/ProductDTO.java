package com.bta.dto;

import com.bta.model.OrderLine;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;

    private String name;

    private Integer skuCode;

    private Integer unitPrice;

    private Integer orderLineCount;
}
