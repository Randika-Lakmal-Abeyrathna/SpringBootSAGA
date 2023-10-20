package com.randikalakmal.orderService.config;

import com.randikalakmal.commondtos.dto.OrderRequestDto;
import com.randikalakmal.commondtos.event.OrderStatus;
import com.randikalakmal.commondtos.event.PaymentStatus;
import com.randikalakmal.orderService.entity.PurchaseOrder;
import com.randikalakmal.orderService.repository.OrderRepository;
import com.randikalakmal.orderService.service.OrderStatusPublisher;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class OrderStatusUpdateHandler {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderStatusPublisher orderStatusPublisher;


    @Transactional
    public void updateOrder(int id, Consumer<PurchaseOrder> consumer){
         orderRepository.findById(id).ifPresent(consumer.andThen(this::updateOrder));
    }

    private void updateOrder(PurchaseOrder purchaseOrder){
        boolean isPaymentCompleted = PaymentStatus.PAYMENT_COMPLETED.equals(purchaseOrder.getPaymentStatus());

        OrderStatus orderStatus = isPaymentCompleted?OrderStatus.ORDER_COMPLETED:OrderStatus.ORDER_CANCELLED;
        purchaseOrder.setOrderStatus(orderStatus);

        if (!isPaymentCompleted){
            orderStatusPublisher.publishOrderEvent(convertEntityToDto(purchaseOrder),orderStatus);
        }

    }

    public OrderRequestDto convertEntityToDto(PurchaseOrder purchaseOrder){
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setOrderId(purchaseOrder.getId());
        orderRequestDto.setUserId(purchaseOrder.getUserId());
        orderRequestDto.setAmount(purchaseOrder.getPrice());
        orderRequestDto.setProductId(purchaseOrder.getProductId());
        return orderRequestDto;
    }
}
