package com.randikalakmal.commondtos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class OrderRequestDto {

    private Integer userId;
    private Integer productId;
    private Integer amount;
    private Integer orderId;

    public OrderRequestDto(Integer userId, Integer productId, Integer amount, Integer orderId) {
        this.userId = userId;
        this.productId = productId;
        this.amount = amount;
        this.orderId = orderId;
    }

    public OrderRequestDto() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
