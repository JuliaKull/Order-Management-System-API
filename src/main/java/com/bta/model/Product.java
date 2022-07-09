package com.bta.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product extends AbstractBaseEntity {

    @Size(max = 100)
    @NotBlank
    private String name;

    @NonNull
    @Column(name = "sku_code")
    private Integer skuCode;

    @NonNull
    @Min(value = 0)
    @Column(name = "unit_price")
    private Integer unitPrice;

    @OneToMany(fetch = EAGER, mappedBy = "product")
    private List<OrderLine> orderLines;
}
