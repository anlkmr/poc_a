package com.cesta.poc_a.mongodb.service;

import com.cesta.poc_a.mongodb.customer.CustomerMongo;
import com.cesta.poc_a.mongodb.repo.CustomerMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerMongoService {
    private CustomerMongoRepository repository;

    @Autowired
    public CustomerMongoService(CustomerMongoRepository repository) {
        this.repository = repository;
    }

    public List<CustomerMongo> retrieveCustomers() {
        return repository.findAll();
    }

    public Optional<CustomerMongo> retrieveCustomers(String id) {
        return repository.findById(id);
    }

    public List<CustomerMongo> retrieveCustomersByName(String name) {
        return repository.findByFirstName(name);
    }

    public CustomerMongo createCustomer(CustomerMongo customerMongo) {
        return repository.save(customerMongo);
    }

    public Optional<CustomerMongo> updateCustomer(String id, CustomerMongo customerMongo) {
        Optional<CustomerMongo> customerOpt = repository.findById(id);
        if(!customerOpt.isPresent()) {
            return customerOpt;
        }
        customerMongo.setId(id);
        return Optional.of(repository.save(customerMongo));
    }

    public boolean deleteCustomer(String id) {
        try {
            repository.deleteById(id);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

}