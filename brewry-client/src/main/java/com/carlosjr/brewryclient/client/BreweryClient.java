package com.carlosjr.brewryclient.client;

import com.carlosjr.brewryclient.model.Beer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "carlosjr.brewery", ignoreUnknownFields = false)
public class BreweryClient {

    private final String BEER_PATH_V1 = "/api/v1/beer/";
    private String apiHost;
    private final RestTemplate restTemplate;
    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    public Beer getBeerById(UUID uuid){
        return restTemplate.getForObject(apiHost + BEER_PATH_V1 + uuid.toString(), Beer.class);
    }
    public URI saveNewBeer(Beer beer){
        return restTemplate.postForLocation(apiHost + BEER_PATH_V1, beer);
    }
    public void updateBeer(UUID uuid, Beer beer){
        restTemplate.put(apiHost + BEER_PATH_V1 + "/" + uuid.toString(), beer);
    }
    public void deleteBeer(UUID uuid){
        restTemplate.delete(apiHost + BEER_PATH_V1 + "/" + uuid.toString());
    }

    public void setApiHost(String apiHost){this.apiHost = apiHost;}

}
