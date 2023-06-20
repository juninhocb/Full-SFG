package com.carlosjr.brewery.web.controller;

import com.carlosjr.brewery.services.BeerService;
import com.carlosjr.brewery.web.model.BeerDto;
import com.carlosjr.brewery.web.model.CustomerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/beer")
public class BeerController {
    private final BeerService beerService;
    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }
    @GetMapping(value = "/{beerId}")
    public ResponseEntity<BeerDto> getBeer (@PathVariable("beerId") UUID beerId){
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity handlePost(@RequestBody BeerDto beerDto){
        BeerDto createdObject = beerService.createBeer(beerDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", String.format("/api/v1/beer/%d", createdObject.getId()));
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
    @PutMapping(value = "/{beerId}")
    public ResponseEntity handlePut(@PathVariable(value = "beerId") UUID beerId, @RequestBody BeerDto beerDto){
        beerService.updateBeer(beerId, beerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping(value = "/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteResource(@PathVariable("beerId") UUID beerId){
        beerService.deleteBeer(beerId);
    }
}
