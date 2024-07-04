package com.cesta.poc_a.mongodb.customer;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class CustomerMongo {
    @Id
    private String id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private Integer age;
    @Email
    private String email;

}