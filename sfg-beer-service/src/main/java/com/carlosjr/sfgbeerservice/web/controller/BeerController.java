package com.carlosjr.sfgbeerservice.web.controller;

import com.carlosjr.sfgbeerservice.domain.Beer;
import com.carlosjr.sfgbeerservice.repositories.BeerRepository;
import com.carlosjr.sfgbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
@RequiredArgsConstructor
public class BeerController {
    private final BeerRepository beerRepository;
    @GetMapping(value = "/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@NotNull @PathVariable("beerId") UUID beerId){
        BeerDto beerDto = new BeerDto();
        Beer beer = beerRepository.findById(beerId).get();
        BeanUtils.copyProperties(beer, beerDto);
        return new ResponseEntity<>(beerDto, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity handlePost(@Valid @RequestBody BeerDto beerDto){
        Beer beer = new Beer();
        BeanUtils.copyProperties(beerDto, beer);
        Beer createdObject = beerRepository.save(beer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", String.format("/api/v1/beer/%s", createdObject.getId().toString()));
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
    @PutMapping(value = "/{beerId}")
    public ResponseEntity handlePut(@PathVariable(value = "beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto){
        Beer beerOnDatabase = beerRepository.findById(beerId).get();
        BeanUtils.copyProperties(beerDto, beerOnDatabase);
        beerRepository.save(beerOnDatabase);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping(value = "/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteResource(@PathVariable("beerId") UUID beerId){
        Beer deleteBeer = beerRepository.findById(beerId).get();
        beerRepository.delete(deleteBeer);
    }
}
