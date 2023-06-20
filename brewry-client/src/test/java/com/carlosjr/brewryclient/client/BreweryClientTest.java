package com.carlosjr.brewryclient.client;

import com.carlosjr.brewryclient.model.BeerDto;
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
        BeerDto dto = client.getBeerById(UUID.randomUUID());
        assertNotNull(dto);
    }
    @Test
    void saveNewBeer() {
        BeerDto beerDto = BeerDto
                .builder()
                .name("New beer")
                .build();
        URI uri = client.saveNewBeer(beerDto);
        assertNotNull(uri);
    }
    @Test
    void updateBeer() {
        BeerDto beerDto = BeerDto
                .builder()
                .name("New beer")
                .build();
        client.updateBeer(UUID.randomUUID(), beerDto);
    }

    @Test
    void deleteBeer() {
        client.deleteBeer(UUID.randomUUID());
    }
}