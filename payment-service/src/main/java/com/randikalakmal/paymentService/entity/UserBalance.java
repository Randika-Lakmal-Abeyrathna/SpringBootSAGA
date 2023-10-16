package com.randikalakmal.paymentService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class UserBalance {

    @Id
    private Integer userId;
    private Integer price;

    public UserBalance(Integer userId, Integer price) {
        this.userId = userId;
        this.price = price;
    }

    public UserBalance() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
