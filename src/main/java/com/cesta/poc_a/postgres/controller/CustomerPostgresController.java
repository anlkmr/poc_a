package com.cesta.poc_a.postgres.controller;

import com.cesta.poc_a.postgres.model.CustomerPostgres;
import com.cesta.poc_a.postgres.model.dto.CreateCustomerDto;
import com.cesta.poc_a.postgres.service.CustomerPostgresService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postgres")
@Qualifier("customercontroller_postgres")
public class CustomerPostgresController {

    private final CustomerPostgresService customerPostgresService;


    public CustomerPostgresController(CustomerPostgresService customerPostgresService) {
        this.customerPostgresService = customerPostgresService;
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerPostgres>> getAllCustomer() {
        try {
            List<CustomerPostgres> allCustomerPostgres = customerPostgresService.getAllCustomers();
            if (allCustomerPostgres.isEmpty()) {
                //logger.info("No customers found");
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(allCustomerPostgres);
        } catch (Exception e) {
           // logger.error("Error fetching customers", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<CreateCustomerDto> createCustomer(@RequestBody CreateCustomerDto dto) {
        CustomerPostgres customerSaved = customerPostgresService.createCustomer(dto);
        return ResponseEntity.ok(dto);
    }
}
