package com.randikalakmal.orderService.entity;


import com.randikalakmal.commondtos.event.OrderStatus;
import com.randikalakmal.commondtos.event.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PURCHASE_ORDER_TBL")
public class PurchaseOrder {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer price;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    public PurchaseOrder(Integer id, Integer userId, Integer productId, Integer price, OrderStatus orderStatus, PaymentStatus paymentStatus) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.price = price;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
    }

    public PurchaseOrder() {
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getPrice() {
        return price;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
}
