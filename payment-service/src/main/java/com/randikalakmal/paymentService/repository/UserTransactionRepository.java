package com.randikalakmal.paymentService.repository;

import com.randikalakmal.paymentService.entity.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransaction,Integer> {
}
