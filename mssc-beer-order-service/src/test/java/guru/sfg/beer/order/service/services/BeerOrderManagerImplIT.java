package guru.sfg.beer.order.service.services;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import guru.sfg.beer.order.service.domain.BeerOrder;
import guru.sfg.beer.order.service.domain.BeerOrderLine;
import guru.sfg.beer.order.service.domain.BeerOrderStatusEnum;
import guru.sfg.beer.order.service.domain.Customer;
import guru.sfg.beer.order.service.repositories.BeerOrderRepository;
import guru.sfg.beer.order.service.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.jgroups.util.Util.assertEquals;
import static org.jgroups.util.Util.assertNotNull;

@WireMockTest
public class BeerOrderManagerImplIT {
    @Autowired
    private BeerOrderManager beerOrderManager;
    @Autowired
    private BeerOrderRepository beerOrderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    private Customer testCustomer;
    private UUID beerId = UUID.randomUUID();

    @BeforeEach
    void setUp(){
        testCustomer = customerRepository.save(Customer
                .builder()
                .customerName("Test Customer").build());
    }

    @Test
    void testNewToAllocated(){
        BeerOrder beerOrder = createBeerOrder();

        BeerOrder savedBeerOrder = beerOrderManager.newBeerOrder(beerOrder);

        assertNotNull(savedBeerOrder);
        assertEquals(BeerOrderStatusEnum.ALLOCATED, savedBeerOrder.getOrderStatus());


    }

    private BeerOrder createBeerOrder(){
        BeerOrder beerOrder = BeerOrder.builder()
                .customer(testCustomer)
                .build();

        Set<BeerOrderLine> lines = new HashSet<>();
        lines.add(BeerOrderLine.builder()
                .beerId(beerId)
                .orderQuantity(1)
                .beerOrder(beerOrder)
                .build());

        beerOrder.setBeerOrderLines(lines);
        return  beerOrder;
    }

}
