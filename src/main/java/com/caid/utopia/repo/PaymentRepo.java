package com.caid.utopia.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.caid.utopia.entity.Account;
import com.caid.utopia.entity.Payment;
import com.caid.utopia.entity.Traveler;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer>{

		@Query("FROM Payment WHERE account = :curr ")
		List<Traveler> AccountHasPayments(@Param("curr") Account curr);
}
