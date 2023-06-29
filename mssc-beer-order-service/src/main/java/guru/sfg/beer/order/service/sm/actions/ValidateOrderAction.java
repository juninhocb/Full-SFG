package guru.sfg.beer.order.service.sm.actions;

import guru.sfg.beer.order.service.config.JmsConfig;
import guru.sfg.beer.order.service.domain.BeerOrder;
import guru.sfg.beer.order.service.domain.BeerOrderEventEnum;
import guru.sfg.beer.order.service.domain.BeerOrderStatusEnum;
import guru.sfg.beer.order.service.repositories.BeerOrderRepository;
import guru.sfg.beer.order.service.services.BeerOrderManagerImpl;
import guru.sfg.beer.order.service.web.mappers.BeerMapper;
import guru.sfg.brewery.events.ValidateOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidateOrderAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum>{

    private final BeerOrderRepository beerOrderRepository;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> stateContext) {
        String beerOrderId = (String) stateContext.getMessage().getHeaders().get(BeerOrderManagerImpl.ORDER_ID_HEADER);
        log.info("Try to validate an order with ID: " + beerOrderId);
        BeerOrder beerOrder = beerOrderRepository.findOneById(UUID.fromString(beerOrderId));
        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_QUEUE, ValidateOrderRequest.builder().beerOrder(beerMapper.beerOrderToDto(beerOrder)).build());

        boolean isApproved = true;
        if (isApproved){
            sendMessageToMachine(stateContext, BeerOrderEventEnum.VALIDATION_PASSED);
        } else {
            sendMessageToMachine(stateContext, BeerOrderEventEnum.VALIDATION_FAILED);
        }
    }

    private void sendMessageToMachine(StateContext stateContext, BeerOrderEventEnum payload){
        stateContext.getStateMachine().sendEvent(MessageBuilder
                .withPayload(payload)
                .setHeader(BeerOrderManagerImpl.ORDER_ID_HEADER, stateContext.getMessageHeader(BeerOrderManagerImpl.ORDER_ID_HEADER))
                .build());

    }
}