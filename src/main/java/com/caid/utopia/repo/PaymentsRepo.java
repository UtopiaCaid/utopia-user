package com.caid.utopia.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caid.utopia.entity.Payments;

@Repository
public interface PaymentsRepo extends JpaRepository<Payments, Integer>{

}
