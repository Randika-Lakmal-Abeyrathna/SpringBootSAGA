package com.randikalakmal.orderService.controller;

import com.randikalakmal.commondtos.dto.OrderRequestDto;
import com.randikalakmal.orderService.entity.PurchaseOrder;
import com.randikalakmal.orderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/create")
    public ResponseEntity<PurchaseOrder> createOrder(@RequestBody OrderRequestDto orderRequestDto){

        PurchaseOrder order = orderService.createOrder(orderRequestDto);

        return new ResponseEntity<>(order, HttpStatus.CREATED);

    }

}
