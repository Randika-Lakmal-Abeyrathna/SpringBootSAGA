package com.randikalakmal.orderService.service;

import com.randikalakmal.commondtos.dto.OrderRequestDto;
import com.randikalakmal.commondtos.event.OrderStatus;
import com.randikalakmal.orderService.entity.PurchaseOrder;
import com.randikalakmal.orderService.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderStatusPublisher orderStatusPublisher;

    @Transactional
    public PurchaseOrder createOrder(OrderRequestDto orderRequest){

        PurchaseOrder order = orderRepository.save(convertDtoToEntity(orderRequest));
        orderRequest.setOrderId(order.getId());

        //publish Kafka event with status
        orderStatusPublisher.publishOrderEvent(orderRequest,OrderStatus.ORDER_CREATED);

        return order;
    }

    public List<PurchaseOrder> getAllOrders(){
        return orderRepository.findAll();
    }


    private PurchaseOrder convertDtoToEntity(OrderRequestDto orderRequestDto){
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProductId(orderRequestDto.getProductId());
        purchaseOrder.setUserId(orderRequestDto.getUserId());
        purchaseOrder.setPrice(orderRequestDto.getAmount());
        purchaseOrder.setOrderStatus(OrderStatus.ORDER_CREATED);
        return purchaseOrder;
    }
}
