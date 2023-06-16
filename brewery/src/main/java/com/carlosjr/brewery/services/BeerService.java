package com.carlosjr.brewery.services;

import com.carlosjr.brewery.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerId);

}
