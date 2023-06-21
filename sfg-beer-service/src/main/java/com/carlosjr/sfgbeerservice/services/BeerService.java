package com.carlosjr.sfgbeerservice.services;

import com.carlosjr.sfgbeerservice.domain.Beer;
import com.carlosjr.sfgbeerservice.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BeerService {
    //todo: impl
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder().build();
    }

    public BeerDto createBeer(BeerDto beerDto) {
        return BeerDto.builder().build();
    }

    public void updateBeer(UUID beerId, BeerDto beerDto) {
    }

    public void deleteBeer(UUID beerId) {
    }
}
