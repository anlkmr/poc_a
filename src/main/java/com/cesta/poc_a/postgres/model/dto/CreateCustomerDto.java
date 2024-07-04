package com.cesta.poc_a.postgres.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateCustomerDto {

    private String firstName;

    private String lastName;

}
