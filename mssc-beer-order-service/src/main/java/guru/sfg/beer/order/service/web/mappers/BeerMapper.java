package guru.sfg.beer.order.service.web.mappers;

import guru.sfg.beer.order.service.domain.BeerOrder;
import guru.sfg.brewery.model.BeerOrderDto;
import org.springframework.stereotype.Component;

@Component
public class BeerMapper {

    public BeerOrderDto beerOrderToDto(BeerOrder beerOrder){
        return BeerOrderDto
                .builder()
                .id(beerOrder.getId())
                .build();
    }

}
