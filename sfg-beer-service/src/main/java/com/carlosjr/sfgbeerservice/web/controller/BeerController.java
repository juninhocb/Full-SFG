package com.carlosjr.sfgbeerservice.web.controller;

import com.carlosjr.sfgbeerservice.services.BeerService;
import com.carlosjr.sfgbeerservice.web.model.BeerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class BeerController {
    private final BeerService beerService;
    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }
    @GetMapping(value = "/{beerId}")
    public ResponseEntity<BeerDto> getBeer (@NotNull @PathVariable("beerId") UUID beerId){
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity handlePost(@Valid @RequestBody BeerDto beerDto){
        BeerDto createdObject = beerService.createBeer(beerDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", String.format("/api/v1/beer/%d", createdObject.getId()));
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
    @PutMapping(value = "/{beerId}")
    public ResponseEntity handlePut(@PathVariable(value = "beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto){
        beerService.updateBeer(beerId, beerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping(value = "/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteResource(@PathVariable("beerId") UUID beerId){
        beerService.deleteBeer(beerId);
    }
}
