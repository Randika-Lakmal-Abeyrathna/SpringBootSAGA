package com.randikalakmal.orderService.repository;

import com.randikalakmal.orderService.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<PurchaseOrder,Integer> {

}
