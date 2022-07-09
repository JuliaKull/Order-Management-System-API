package com.bta.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.math.BigInteger;

@Data
@Builder
public class CustomerDTO {

    private String firstName;

    private String lastName;

    private BigInteger registrationCode;

    private String email;

    private String telephone;

}
