package com.carlosjr.sfgbeerservice.bootstrap;

import com.carlosjr.sfgbeerservice.repositories.BeerRepository;
import com.carlosjr.sfgbeerservice.web.model.BeerStyleEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.carlosjr.sfgbeerservice.domain.Beer;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;
    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {

        if(beerRepository.count() == 0){
            beerRepository.save(Beer
                    .builder()
                    .beerName("Kayser")
                    .beerStyle(BeerStyleEnum.PILSEN)
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(28374794827L)
                    .price(new BigDecimal("4.40"))
                    .build());
            beerRepository.save(Beer
                    .builder()
                    .beerName("Heineken")
                    .beerStyle(BeerStyleEnum.GOSE)
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(283747912423L)
                    .price(new BigDecimal("7.40"))
                    .build());
            beerRepository.save(Beer
                    .builder()
                    .beerName("Brahma")
                    .beerStyle(BeerStyleEnum.IPA)
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(28374794111L)
                    .price(new BigDecimal("5.40"))
                    .build());
        }
        System.out.println("Loaded beers: " + beerRepository.count());
    }
}
