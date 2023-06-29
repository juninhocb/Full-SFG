package guru.sfg.beer.order.service.web.mappers;

import guru.sfg.beer.order.service.domain.BeerOrder;
import guru.sfg.beer.order.service.domain.BeerOrderLine;
import guru.sfg.brewery.model.BeerOrderDto;
import guru.sfg.brewery.model.BeerOrderLineDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BeerMapper {

    public BeerOrderDto beerOrderToDto(BeerOrder beerOrder){
        return BeerOrderDto
                .builder()
                .id(beerOrder.getId())
                .beerOrderLines(beerOrder.getBeerOrderLines().stream().map(beerOrderLine -> beerOrderLineToDto(beerOrderLine)).collect(Collectors.toList()))
                .build();
    }
    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine beerOrderLine){
        return BeerOrderLineDto.builder()
                .beerId(beerOrderLine.getBeerId())
                .upc(beerOrderLine.getUpc())
                .orderQuantity(beerOrderLine.getOrderQuantity())
                .quantityAllocated(beerOrderLine.getQuantityAllocated())
                .build();


    }

}
