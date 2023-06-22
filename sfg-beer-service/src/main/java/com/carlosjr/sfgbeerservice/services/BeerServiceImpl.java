package com.carlosjr.sfgbeerservice.services;

import com.carlosjr.sfgbeerservice.domain.Beer;
import com.carlosjr.sfgbeerservice.repositories.BeerRepository;
import com.carlosjr.sfgbeerservice.web.controller.ResourceNotFoundException;
import com.carlosjr.sfgbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;

    public BeerDto getBeerById(UUID beerId) {
        BeerDto beerDto = new BeerDto();
        Beer beer = beerRepository.findById(beerId).orElseThrow(() -> new ResourceNotFoundException("Resource was not found"));
        BeanUtils.copyProperties(beer, beerDto);
        return beerDto;
    }

    public UUID createBeer(BeerDto beerDto) {
        Beer beer = new Beer();
        BeanUtils.copyProperties(beerDto, beer);
        Beer createdObject = beerRepository.save(beer);
        return createdObject.getId();
    }

    public void updateBeer(UUID beerId, BeerDto beerDto) {
    }

    public void deleteBeer(UUID beerId) {
        Beer beer = new Beer();
        BeanUtils.copyProperties(getBeerById(beerId), beer);
        beerRepository.delete(beer);
    }
}
