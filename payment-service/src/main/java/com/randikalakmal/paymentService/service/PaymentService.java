package com.randikalakmal.paymentService.service;

import com.randikalakmal.commondtos.dto.OrderRequestDto;
import com.randikalakmal.commondtos.dto.PaymentRequestDto;
import com.randikalakmal.commondtos.event.OrderEvent;
import com.randikalakmal.commondtos.event.PaymentEvent;
import com.randikalakmal.commondtos.event.PaymentStatus;
import com.randikalakmal.paymentService.entity.UserBalance;
import com.randikalakmal.paymentService.entity.UserTransaction;
import com.randikalakmal.paymentService.repository.UserBalanceRepository;
import com.randikalakmal.paymentService.repository.UserTransactionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PaymentService {

    @Autowired
    private UserBalanceRepository userBalanceRepository;

    @Autowired
    private UserTransactionRepository userTransactionRepository;

    @PostConstruct
    public void initUserBalanceInDB(){
        userBalanceRepository.saveAll(
                Stream.of(new UserBalance(101,5000),
                                new UserBalance(102,3000),
                        new UserBalance(103,4200),
                        new UserBalance(104,20000),
                        new UserBalance(105,999)
                                )
                        .collect(Collectors.toList())
        );

    }


    public PaymentEvent newOrderEvent(OrderEvent orderEvent) {
        /**
         *
         * Get the user ID
         * Check the balance availability
         * If balance sufficient --> payment complete and deduct amount form DB
         * If payment not sufficient --> cancel the order event and update the amount in DB
         *
         * **/
        OrderRequestDto orderRequestDto = orderEvent.getOrderRequestDto();
        PaymentRequestDto paymentRequestDto= new PaymentRequestDto(orderRequestDto.getOrderId(),orderRequestDto.getUserId(),orderRequestDto.getAmount());

        return userBalanceRepository.findById(orderRequestDto.getUserId())
                .filter(userBalance -> userBalance.getPrice() >orderRequestDto.getAmount())
                .map( userBalance -> {
                        userBalance.setPrice(userBalance.getPrice() - orderRequestDto.getAmount());
                        userTransactionRepository.save(new UserTransaction(orderRequestDto.getOrderId(),orderRequestDto.getUserId(),orderRequestDto.getAmount()));
                        return new PaymentEvent(paymentRequestDto,PaymentStatus.PAYMENT_COMPLETED);
                        }
                ).orElse(new PaymentEvent(paymentRequestDto, PaymentStatus.PAYMENT_FAILED));

    }

    public void cancelOrderEvent(OrderEvent orderEvent) {
        userTransactionRepository.findById(orderEvent.getOrderRequestDto().getOrderId())
                .ifPresent(userTransaction -> {
                    userTransactionRepository.delete(userTransaction);
                    userTransactionRepository.findById(userTransaction.getUserId())
                            .ifPresent(ub -> ub.setAmount(ub.getAmount() + userTransaction.getAmount()) );
                });
    }
}
