package com.cesta.poc_a.postgres.repository;


import com.cesta.poc_a.postgres.model.CustomerPostgres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerPostgresRepository extends JpaRepository<CustomerPostgres, Long> {
}
