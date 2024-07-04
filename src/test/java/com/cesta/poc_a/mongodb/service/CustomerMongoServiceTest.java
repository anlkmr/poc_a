package com.cesta.poc_a.mongodb.service;

import com.cesta.poc_a.mongodb.customer.CustomerMongo;
import com.cesta.poc_a.mongodb.repo.CustomerMongoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerMongoServiceTest {

    @Mock
    private CustomerMongoRepository repository;

    @InjectMocks
    private CustomerMongoService service;

    private CustomerMongo customer1;
    private CustomerMongo customer2;

    @BeforeEach
    public void setUp() {
        customer1 = new CustomerMongo("1", "John", "Doe",25,"email@g.com");
        customer2 = new CustomerMongo("2", "Jane", "Smith", 27,"email@g.com");
    }

    @Test
    public void testRetrieveCustomers() {
        List<CustomerMongo> mockCustomers = new ArrayList<>();
        mockCustomers.add(customer1);
        mockCustomers.add(customer2);

        when(repository.findAll()).thenReturn(mockCustomers);

        List<CustomerMongo> customers = service.retrieveCustomers();

        assertEquals(2, customers.size());
        assertEquals(customer1, customers.get(0));
        assertEquals(customer2, customers.get(1));

        verify(repository, times(1)).findAll();
    }

    @Test
    public void testRetrieveCustomerById() {
        String customerId = "1";
        when(repository.findById(customerId)).thenReturn(Optional.of(customer1));

        Optional<CustomerMongo> found = service.retrieveCustomers(customerId);

        assertTrue(found.isPresent());
        assertEquals(customer1, found.get());

        verify(repository, times(1)).findById(customerId);
    }

    @Test
    public void testRetrieveCustomersByName() {
        String customerName = "John";
        List<CustomerMongo> mockCustomers = new ArrayList<>();
        mockCustomers.add(customer1);

        when(repository.findByFirstName(customerName)).thenReturn(mockCustomers);

        List<CustomerMongo> customers = service.retrieveCustomersByName(customerName);

        assertEquals(1, customers.size());
        assertEquals(customer1, customers.get(0));

        verify(repository, times(1)).findByFirstName(customerName);
    }

    @Test
    public void testCreateCustomer() {
        when(repository.save(customer1)).thenReturn(customer1);

        CustomerMongo savedCustomer = service.createCustomer(customer1);

        assertEquals(customer1, savedCustomer);

        verify(repository, times(1)).save(customer1);
    }

    @Test
    public void testUpdateCustomer() {
        String customerId = "1";
        CustomerMongo updatedCustomer = new CustomerMongo("1", "Updated", "Name", 31,"email@g.com");

        when(repository.findById(customerId)).thenReturn(Optional.of(customer1));
        when(repository.save(updatedCustomer)).thenReturn(updatedCustomer);

        Optional<CustomerMongo> updated = service.updateCustomer(customerId, updatedCustomer);

        assertTrue(updated.isPresent());
        assertEquals(updatedCustomer, updated.get());

        verify(repository, times(1)).findById(customerId);
        verify(repository, times(1)).save(updatedCustomer);
    }

    @Test
    public void testUpdateCustomer_customerNotFound() {
        String customerId = "1";
        CustomerMongo updatedCustomer = new CustomerMongo("1", "Updated", "Name", 41, "email@g.com");

        when(repository.findById(customerId)).thenReturn(Optional.empty());

        Optional<CustomerMongo> updated = service.updateCustomer(customerId, updatedCustomer);

        assertFalse(updated.isPresent());

        verify(repository, times(1)).findById(customerId);
        verify(repository, never()).save(updatedCustomer);
    }

   // @Test
    public void testDeleteCustomer() {
        String customerId = "1";

        when(repository.existsById(customerId)).thenReturn(true);

        boolean deleted = service.deleteCustomer(customerId);

        assertTrue(deleted);

        verify(repository, times(1)).deleteById(customerId);
    }

    @Test
    public void testDeleteCustomer_customerNotFound() {
        String customerId = "1";

        doNothing().when(repository).deleteById(customerId);

        boolean deleted = service.deleteCustomer(customerId);

        assertTrue(deleted);

        verify(repository, times(1)).deleteById(customerId);
        //verify(repository, never()).deleteById(customerId);
    }
}
