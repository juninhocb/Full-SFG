package com.carlosjr.brewery.services;

import com.carlosjr.brewery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {
    CustomerDto getCustomerById(UUID customerId);

}
