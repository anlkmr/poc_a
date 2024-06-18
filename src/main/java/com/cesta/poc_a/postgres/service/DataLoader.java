package com.cesta.poc_a.postgres.service;

import com.cesta.poc_a.postgres.model.CustomerPostgres;
import com.cesta.poc_a.postgres.repository.CustomerPostgresRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private final CustomerPostgresRepository customerPostgresRepository;


    public DataLoader(CustomerPostgresRepository customerPostgresRepository) {
        this.customerPostgresRepository = customerPostgresRepository;
    }

    @PostConstruct
    public void init() {
        customerPostgresRepository.save(new CustomerPostgres("Gozde", "Yalcin"));
        customerPostgresRepository.save(new CustomerPostgres("Boncuk", "Yalcin"));
    }
}
