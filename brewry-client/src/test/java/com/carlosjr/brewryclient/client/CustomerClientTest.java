package com.carlosjr.brewryclient.client;

import com.carlosjr.brewryclient.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerClientTest {
    @Autowired
    private CustomerClient client;
    @Test
    void getCustomerById() {
        CustomerDto customerDto = client.getCustomerById(UUID.randomUUID());
        assertNotNull(customerDto);
    }
    @Test
    void saveNewCustomer() {
        CustomerDto customerDto = CustomerDto.builder().name("New Costumer").build();
        URI uri = client.saveNewCustomer(customerDto);
        assertNotNull(uri);
    }
    @Test
    void updateCustomer() {
        CustomerDto customerDto = CustomerDto.builder().name("New Costumer").build();
        client.updateCustomer(UUID.randomUUID(), customerDto);
    }
    @Test
    void deleteCustomer() {
        client.deleteCustomer(UUID.randomUUID());
    }
}