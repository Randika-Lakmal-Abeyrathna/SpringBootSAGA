package com.randikalakmal.paymentService.config;

import com.randikalakmal.commondtos.event.OrderEvent;
import com.randikalakmal.commondtos.event.OrderStatus;
import com.randikalakmal.commondtos.event.PaymentEvent;
import com.randikalakmal.paymentService.service.PaymentService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class PaymentConsumerConfig {

    @Autowired
    private PaymentService paymentService;

    @Bean
    public Function<Flux<OrderEvent>,Flux<PaymentEvent>> paymentProcessor(){

        return orderEventFlux -> orderEventFlux.flatMap(this::processPayment);
    }

    private Mono<PaymentEvent> processPayment(OrderEvent orderEvent) {

        /**
         *
         * Get the user ID
         * Check the balance availability
         * If balance sufficient --> payment complete and deduct amount form DB
         * If payment not sufficient --> cancel the order event and update the amount in DB
         *
         * **/

        if (OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus())){
            return Mono.fromSupplier(()->this.paymentService.newOrderEvent(orderEvent));
        }else {
            return Mono.fromRunnable(()-> this.paymentService.cancelOrderEvent(orderEvent));
        }

    }

}
