package com.bta.model;


import lombok.*;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.List;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer extends AbstractBaseEntity {


    @Size(max = 20)
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 20)
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "registration_code")
    private BigInteger registrationCode;

    @Size(max = 50)
    private String email;

    @Size(max = 50)
    private String telephone;


 @ToString.Exclude
    @OneToMany(mappedBy = "customer",
            cascade = PERSIST,
            fetch = LAZY)
    private List<CustomerOrder> customerOrders;

}
