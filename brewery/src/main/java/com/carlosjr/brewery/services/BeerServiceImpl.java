package com.carlosjr.brewery.services;

import com.carlosjr.brewery.web.model.BeerDto;
import com.carlosjr.brewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("IPA")
                .beerStyle("Citric")
                .build();
    }
    @Override
    public BeerDto createBeer(BeerDto beerDto) {
        return beerDto;
    }
    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {}
    @Override
    public void deleteBeer(UUID beerId) {
        log.info("Deleting beer with id: " + beerId);
    }
}
