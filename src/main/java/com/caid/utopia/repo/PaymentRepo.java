package com.caid.utopia.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caid.utopia.entity.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer>{

}
