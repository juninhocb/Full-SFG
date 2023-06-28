package guru.sfg.beer.order.service.web.mappers;

import guru.sfg.beer.order.service.domain.BeerOrder;
import guru.sfg.beer.order.service.domain.BeerOrderLine;
import guru.sfg.brewery.model.BeerOrderDto;
import guru.sfg.brewery.model.BeerOrderLineDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.sql.Timestamp;
import java.util.UUID;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-28T13:50:43-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.9.1 (AdoptOpenJDK)"
)
@Component
@Qualifier("delegate")
public class BeerOrderLineMapperImpl_ implements BeerOrderLineMapper {

    @Override
    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
        if ( line == null ) {
            return null;
        }

        BeerOrderLineDto beerOrderLineDto = new BeerOrderLineDto();

        return beerOrderLineDto;
    }

    @Override
    public BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto) {
        if ( dto == null ) {
            return null;
        }

        UUID id = null;
        Long version = null;
        Timestamp createdDate = null;
        Timestamp lastModifiedDate = null;
        BeerOrder beerOrder = null;
        UUID beerId = null;
        String upc = null;
        Integer orderQuantity = null;
        Integer quantityAllocated = null;

        BeerOrderLine beerOrderLine = new BeerOrderLine( id, version, createdDate, lastModifiedDate, beerOrder, beerId, upc, orderQuantity, quantityAllocated );

        return beerOrderLine;
    }
}
