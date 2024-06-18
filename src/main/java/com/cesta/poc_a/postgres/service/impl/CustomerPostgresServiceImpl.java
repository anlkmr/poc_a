package com.cesta.poc_a.postgres.service.impl;

import com.cesta.poc_a.postgres.model.CustomerPostgres;
import com.cesta.poc_a.postgres.model.dto.CreateCustomerDto;
import com.cesta.poc_a.postgres.repository.CustomerPostgresRepository;
import com.cesta.poc_a.postgres.service.CustomerPostgresService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class CustomerPostgresServiceImpl implements CustomerPostgresService {

    private final CustomerPostgresRepository customerPostgresRepository;

    public CustomerPostgresServiceImpl(CustomerPostgresRepository customerPostgresRepository) {
        this.customerPostgresRepository = customerPostgresRepository;
    }

    @Override
    public List<CustomerPostgres> getAllCustomers() {
        return customerPostgresRepository.findAll();
    }

    @Override
    public CustomerPostgres createCustomer(CreateCustomerDto dto) {

        if (ObjectUtils.isEmpty(dto)) {
            throw new IllegalArgumentException("customer is empty");
        }
        CustomerPostgres customerPostgres = CustomerPostgres.builder().firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build();
        CustomerPostgres saved = customerPostgresRepository.save(customerPostgres);
        return saved;
    }
}
