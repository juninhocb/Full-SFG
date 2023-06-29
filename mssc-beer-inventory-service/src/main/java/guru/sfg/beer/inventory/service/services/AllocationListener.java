package guru.sfg.beer.inventory.service.services;

import guru.sfg.beer.inventory.service.config.JmsConfig;
import guru.sfg.brewery.model.events.AllocateOrderRequest;
import guru.sfg.brewery.model.events.AllocateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AllocationListener {

    private final JmsTemplate jmsTemplate;
    private final AllocationService allocationService;

    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_QUEUE)
    public void listen(AllocateOrderRequest request){

        AllocateOrderResult.AllocateOrderResultBuilder builder = AllocateOrderResult.builder()
                .beerOrderDto(request.getBeerOrderDto());

        try {
            Boolean allocationResult = allocationService.allocateOrder(request.getBeerOrderDto());
            if (allocationResult){
                builder.pendingInventory(false);
            } else {
                builder.pendingInventory(true);
            }
        }catch (Exception ex){
            log.error("Allocation failed for Order id: " + request.getBeerOrderDto().getId() + "\n" + ex.getMessage());
            builder.allocationError(true);
        }

        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE, builder.build());



    }

}
