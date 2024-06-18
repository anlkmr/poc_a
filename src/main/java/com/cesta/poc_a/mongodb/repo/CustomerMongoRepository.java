package com.cesta.poc_a.mongodb.repo;

import com.cesta.poc_a.mongodb.customer.CustomerMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerMongoRepository extends MongoRepository<CustomerMongo, String> {
    List<CustomerMongo> findByFirstName(String firstName);

}
