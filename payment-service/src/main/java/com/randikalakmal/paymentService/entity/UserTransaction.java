package com.randikalakmal.paymentService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class UserTransaction {

    public UserTransaction(Integer orderId, Integer userId, Integer amount) {
        this.orderId = orderId;
        this.userId = userId;
        this.amount = amount;
    }

    public UserTransaction() {
    }

    @Id
    private Integer orderId;
    private Integer userId;
    private Integer amount;

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
