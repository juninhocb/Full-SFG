package guru.sfg.beer.inventory.service.web.mappers;

import guru.sfg.beer.inventory.service.domain.BeerInventory;
import guru.sfg.beer.inventory.service.web.model.BeerInventoryDto;
import java.sql.Timestamp;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-23T11:15:49-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.9.1 (AdoptOpenJDK)"
)
@Component
public class BeerInventoryMapperImpl implements BeerInventoryMapper {

    @Override
    public BeerInventory beerInventoryDtoToBeerInventory(BeerInventoryDto beerInventoryDTO) {
        if ( beerInventoryDTO == null ) {
            return null;
        }

        UUID id = null;
        Long version = null;
        Timestamp createdDate = null;
        Timestamp lastModifiedDate = null;
        UUID beerId = null;
        String upc = null;
        Integer quantityOnHand = null;

        BeerInventory beerInventory = new BeerInventory( id, version, createdDate, lastModifiedDate, beerId, upc, quantityOnHand );

        return beerInventory;
    }

    @Override
    public BeerInventoryDto beerInventoryToBeerInventoryDto(BeerInventory beerInventory) {
        if ( beerInventory == null ) {
            return null;
        }

        BeerInventoryDto beerInventoryDto = new BeerInventoryDto();

        return beerInventoryDto;
    }
}
