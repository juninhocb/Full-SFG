package com.carlosjr.brewery.web.controller;

import com.carlosjr.brewery.services.CustomerService;
import com.carlosjr.brewery.web.model.CustomerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping(value = "/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(value = "customerId") UUID customerId){
        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity handlePost(@RequestBody CustomerDto customerDto){
        CustomerDto createdObject = customerService.createCustomer(customerDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", String.format("/api/v1/customer/%s", createdObject.getId()));
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
    @PutMapping(value = "/{customerId}")
    public ResponseEntity handlePut(@PathVariable(value = "customerId") UUID customerId, @RequestBody CustomerDto customerDto){
        customerService.updateCustomer(customerId, customerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping(value = "/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable(value = "customerId") UUID customerId){
        customerService.deleteCustomer(customerId);
    }
}
