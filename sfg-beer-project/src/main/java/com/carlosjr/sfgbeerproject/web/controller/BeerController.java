package com.carlosjr.sfgbeerproject.web.controller;

import com.carlosjr.sfgbeerproject.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    @GetMapping(value = "/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId")UUID id){
        //todo impl
        return new ResponseEntity<>(BeerDto.builder().build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@RequestBody BeerDto beerDto){
        //todo impl
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{beeerId}")
    public ResponseEntity updateBeerId(@PathVariable("beerId")UUID id, @RequestBody BeerDto beerDto){
        //todo impl
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }




}
