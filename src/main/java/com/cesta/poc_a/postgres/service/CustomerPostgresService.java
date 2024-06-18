package com.cesta.poc_a.postgres.service;

import com.cesta.poc_a.postgres.model.CustomerPostgres;
import com.cesta.poc_a.postgres.model.dto.CreateCustomerDto;

import java.util.List;

public interface CustomerPostgresService {

    List<CustomerPostgres> getAllCustomers();

    CustomerPostgres createCustomer(CreateCustomerDto dto);
}
