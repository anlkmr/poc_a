package com.cesta.poc_a.postgres.controller;

import com.cesta.poc_a.postgres.model.CustomerPostgres;
import com.cesta.poc_a.postgres.model.dto.CreateCustomerDto;
import com.cesta.poc_a.postgres.service.CustomerPostgresService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerPostgresControllerTest {

    @Mock
    private CustomerPostgresService customerPostgresService;

    @InjectMocks
    private CustomerPostgresController customerPostgresController;

    private List<CustomerPostgres> mockCustomers;

    @BeforeEach
    public void setup() {
        mockCustomers = new ArrayList<>();
        mockCustomers.add(new CustomerPostgres(1L, "John Doe", "john.doe@example.com"));
        mockCustomers.add(new CustomerPostgres(2L, "Jane Doe", "jane.doe@example.com"));
    }

    @Test
    public void testGetAllCustomer() {
        // Mock service response
        when(customerPostgresService.getAllCustomers()).thenReturn(mockCustomers);

        // Call controller method
        ResponseEntity<List<CustomerPostgres>> response = customerPostgresController.getAllCustomer();

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCustomers, response.getBody());

        // Verify service method was called
        verify(customerPostgresService, times(1)).getAllCustomers();
    }

    @Test
    public void testGetAllCustomer_noCustomers() {
        // Mock service response
        when(customerPostgresService.getAllCustomers()).thenReturn(new ArrayList<>());

        // Call controller method
        ResponseEntity<List<CustomerPostgres>> response = customerPostgresController.getAllCustomer();

        // Verify
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

        // Verify service method was called
        verify(customerPostgresService, times(1)).getAllCustomers();
    }

    @Test
    public void testGetAllCustomer_errorFetchingCustomers() {
        // Mock service to throw exception
        when(customerPostgresService.getAllCustomers()).thenThrow(RuntimeException.class);

        // Call controller method
        ResponseEntity<List<CustomerPostgres>> response = customerPostgresController.getAllCustomer();

        // Verify
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(null, response.getBody());

        // Verify service method was called
        verify(customerPostgresService, times(1)).getAllCustomers();
    }

    @Test
    public void testCreateCustomer() {
        // Prepare input DTO
        CreateCustomerDto dto = new CreateCustomerDto("New Customer", "new.customer@example.com");

        // Mock service response
        CustomerPostgres savedCustomer = new CustomerPostgres(3L, "New Customer", "new.customer@example.com");
        when(customerPostgresService.createCustomer(dto)).thenReturn(savedCustomer);

        // Call controller method
        ResponseEntity<CreateCustomerDto> response = customerPostgresController.createCustomer(dto);

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());

        // Verify service method was called
        verify(customerPostgresService, times(1)).createCustomer(dto);
    }
}
