package com.randikalakmal.commondtos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class PaymentRequestDto {

    private Integer orderId;
    private Integer userId;
    private Integer ammount;


    public PaymentRequestDto(Integer orderId, Integer userId, Integer ammount) {
        this.orderId = orderId;
        this.userId = userId;
        this.ammount = ammount;
    }

    public PaymentRequestDto() {
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

    public Integer getAmmount() {
        return ammount;
    }

    public void setAmmount(Integer ammount) {
        this.ammount = ammount;
    }
}
