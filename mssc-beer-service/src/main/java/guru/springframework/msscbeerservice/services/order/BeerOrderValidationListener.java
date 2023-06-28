package guru.springframework.msscbeerservice.services.order;

import guru.sfg.brewery.events.ValidateOrderRequest;
import guru.sfg.brewery.events.ValidateOrderResult;
import guru.springframework.msscbeerservice.config.JmsConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {

    private final BeerOrderValidator validator;
    private final JmsTemplate jmsTemplate;
    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    private void listen(ValidateOrderRequest validateOrderRequest){

        Boolean isValidFromValidator = validator.validateOrder(validateOrderRequest.getBeerOrder());

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE,
                ValidateOrderResult.builder()
                        .isValid(isValidFromValidator)
                        .orderId(validateOrderRequest.getBeerOrder().getId()).build());
    }
}
