package guru.springframework.msscbeerservice.services.brewing;

import guru.sfg.common.events.BrewBeerEvent;
import guru.springframework.msscbeerservice.config.JmsConfig;
import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.services.BeerService;
import guru.springframework.msscbeerservice.services.inventory.BeerInventoryService;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BrewingService {
    private final BeerService beerService;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory(){
        List<Beer> beers = beerService.getAll();

        beers.forEach(beer -> {
            Integer invQOH = beerInventoryService.getOnhandInventory(beer.getId()); //sync

            log.debug("Min on hand is: " + beer.getMinOnHand());
            log.debug("Inventory is: " + invQOH);
            BeerDto beerDto = new BeerDto();
            BeanUtils.copyProperties(beer, beerDto);
            if (beer.getMinOnHand() >= invQOH){
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerDto)); // async
            }
        });

    }

}
