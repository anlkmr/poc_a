package com.cesta.poc_a.mongodb.controller;

import com.cesta.poc_a.mongodb.customer.CustomerMongo;
import com.cesta.poc_a.mongodb.service.CustomerMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mongo/customers")
@Qualifier("customcontroller_mongo")
public class CustomerMongoController {
    @Autowired
    private CustomerMongoService customerMongoService;

    @GetMapping()
    public ResponseEntity<?> getCustomers() {
        List<CustomerMongo> customerMongos = customerMongoService.retrieveCustomers();
        return ResponseEntity.ok(customerMongos);
    }

    @GetMapping(params = "name")
    public ResponseEntity<?> getCustomers(@RequestParam String name) {
        List<CustomerMongo> customerMongos = customerMongoService.retrieveCustomersByName(name);
        return ResponseEntity.ok(customerMongos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable String id) {
        Optional<?> customer = customerMongoService.retrieveCustomers(id);
        if(!customer.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(customer);
    }

    @PostMapping()
    public ResponseEntity<?> postCustomers(@RequestBody CustomerMongo body) {
        CustomerMongo customerMongo = customerMongoService.createCustomer(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerMongo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putCustomers(@PathVariable String id, @RequestBody CustomerMongo body) {
        Optional<?> customer = customerMongoService.updateCustomer(id, body);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomers(@PathVariable String id) {
        if(!customerMongoService.deleteCustomer(id)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

}