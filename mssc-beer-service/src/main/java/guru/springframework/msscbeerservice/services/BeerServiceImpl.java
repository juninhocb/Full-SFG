package guru.springframework.msscbeerservice.services;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.services.inventory.BeerInventoryService;
import guru.springframework.msscbeerservice.web.controller.NotFoundException;
import guru.springframework.msscbeerservice.web.mappers.BeerMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;
    private final BeerInventoryService inventoryService;

    @Cacheable(cacheNames = "beerCache", key = "#beerId" ,condition = "#showInventoryOnHand == false")
    @Override
    public BeerDto getById(UUID beerId, Boolean showInventoryOnHand) {
        BeerDto beerDto = new BeerDto();
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
        beerDto.setCreatedDate(beer.getCreatedDate().toLocalDateTime());
        beerDto.setLastModifiedDate(beer.getLastModifiedDate().toLocalDateTime());
        beerDto.setBeerStyle(BeerStyleEnum.fromString(beer.getBeerStyle()));
        BeanUtils.copyProperties(beer, beerDto);

        if (showInventoryOnHand){
            beerDto.setQuantityOnHand(inventoryService.getOnhandInventory(beer.getId()));
        }
        return beerDto;
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }

    @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false")
    @Override
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand) {

        BeerPagedList beerPagedList;
        Page<Beer> beerPage;

        if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search both
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
            //search beer_service name
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search beer_service style
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }

        List<BeerDto> listBeerDto = new ArrayList<>();

        if (showInventoryOnHand){
            beerPage.getContent().stream().forEach(b -> {
                BeerDto tempDto = new BeerDto();
                BeanUtils.copyProperties(b, tempDto);
                tempDto.setCreatedDate(b.getCreatedDate().toLocalDateTime());
                tempDto.setLastModifiedDate(b.getLastModifiedDate().toLocalDateTime());
                tempDto.setBeerStyle(BeerStyleEnum.fromString(b.getBeerStyle()));
                tempDto.setQuantityOnHand(inventoryService.getOnhandInventory(b.getId()));
                listBeerDto.add(tempDto);
            });
        } else {
            beerPage.getContent().stream().forEach(b -> {
                BeerDto tempDto = new BeerDto();
                BeanUtils.copyProperties(b, tempDto);
                tempDto.setCreatedDate(b.getCreatedDate().toLocalDateTime());
                tempDto.setLastModifiedDate(b.getLastModifiedDate().toLocalDateTime());
                tempDto.setBeerStyle(BeerStyleEnum.fromString(b.getBeerStyle()));
                listBeerDto.add(tempDto);
            });
        }


        beerPagedList = new BeerPagedList(listBeerDto,
                PageRequest
                        .of(beerPage.getPageable().getPageNumber(),
                                beerPage.getPageable().getPageSize()),
                beerPage.getTotalElements());

        return beerPagedList;
    }

    @Cacheable(cacheNames = "beerUpc", key = "#upc", condition = "#showInventoryOnHand == false")
    @Override
    public BeerDto getByUpc(String upc, Boolean showInventoryOnHand) {
        BeerDto beerDto = new BeerDto();
        Beer beer = beerRepository.findByUpc(upc);
        beerDto.setCreatedDate(beer.getCreatedDate().toLocalDateTime());
        beerDto.setLastModifiedDate(beer.getLastModifiedDate().toLocalDateTime());
        beerDto.setBeerStyle(BeerStyleEnum.fromString(beer.getBeerStyle()));
        BeanUtils.copyProperties(beer, beerDto);
        if (showInventoryOnHand){
            beerDto.setQuantityOnHand(inventoryService.getOnhandInventory(beer.getId()));
        }

        return beerDto;
    }
    @Override
    public List<Beer> getAll() {
        List<Beer> beers = beerRepository.findAll();
        System.out.println("Beer quantity: " + beers.size());
        return beers;
    }
}
