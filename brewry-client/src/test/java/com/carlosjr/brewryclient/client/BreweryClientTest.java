package com.carlosjr.brewryclient.client;

import com.carlosjr.brewryclient.model.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    private BreweryClient client;
    @Test
    void getBeerById() {
        Beer dto = client.getBeerById(UUID.randomUUID());
        assertNotNull(dto);
    }
    @Test
    void saveNewBeer() {
        Beer beer = Beer
                .builder()
                .name("New beer")
                .build();
        URI uri = client.saveNewBeer(beer);
        assertNotNull(uri);
    }
    @Test
    void updateBeer() {
        Beer beer = Beer
                .builder()
                .name("New beer")
                .build();
        client.updateBeer(UUID.randomUUID(), beer);
    }

    @Test
    void deleteBeer() {
        client.deleteBeer(UUID.randomUUID());
    }
}