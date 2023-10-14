package com.randikalakmal.orderService.service;

import com.randikalakmal.commondtos.dto.OrderRequestDto;
import com.randikalakmal.orderService.entity.PurchaseOrder;
import com.randikalakmal.orderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public PurchaseOrder createOrder(OrderRequestDto orderRequest){

//        orderRepository.save();
        return null;
    }

    private PurchaseOrder convertDtoToEntity(OrderRequestDto orderRequestDto){
        PurchaseOrder purchaseOrder = new PurchaseOrder();
//        purchaseOrder.setProductId(orderRequestDto.g);
        return purchaseOrder;
    }
}
