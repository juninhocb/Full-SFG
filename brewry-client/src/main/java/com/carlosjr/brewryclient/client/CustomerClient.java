package com.carlosjr.brewryclient.client;

import com.carlosjr.brewryclient.model.CustomerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
public class CustomerClient {
    private String BASE_CUSTOMER_V1 = "/api/v1/customer";
    @Value("${carlosjr.brewery.api-host}")
    private String apiHost;
    private final RestTemplate restTemplate;
    public CustomerClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }
    public CustomerDto getCustomerById(UUID uuid){
        return restTemplate.getForObject(apiHost + BASE_CUSTOMER_V1 + "/" + uuid, CustomerDto.class);
    }
    public URI saveNewCustomer(CustomerDto customerDto){
        return restTemplate.postForLocation(apiHost + BASE_CUSTOMER_V1, customerDto);
    }
    public void updateCustomer(UUID uuid, CustomerDto customerDto){
        restTemplate.put(apiHost + BASE_CUSTOMER_V1 + "/" + uuid.toString(), customerDto);
    }
    public void deleteCustomer(UUID uuid){
        restTemplate.delete(apiHost + BASE_CUSTOMER_V1 + "/" + uuid.toString());
    }

}
