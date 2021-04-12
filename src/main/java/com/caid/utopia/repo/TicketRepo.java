package com.caid.utopia.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.caid.utopia.entity.Account;
import com.caid.utopia.entity.Flight;
import com.caid.utopia.entity.Payment;
import com.caid.utopia.entity.Ticket;
import com.caid.utopia.entity.Traveler;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Integer>{
	@Query("FROM Ticket WHERE flight = :curr ")
	List<Ticket> FlightHasTickets(@Param("curr") Flight curr);
	
	@Query("FROM Ticket WHERE payment = :curr ")
	List<Ticket> PaymentHasTickets(@Param("curr") Payment curr);
	
	@Query("FROM Ticket WHERE traveler = :curr ")
	List<Ticket> TravelerHasTickets(@Param("curr") Traveler curr);

	/* user queries */
	@Query("FROM Ticket t "
			+ "JOIN t.traveler tr WHERE "
			+ "t.traveler = tr AND tr.account = :account")		
	Set<Ticket> FindAccountTicketHistory(@Param("account") Account account);
	
	@Query("FROM Ticket t "
			+ "JOIN t.traveler tr "
			+ "JOIN t.flight f WHERE "
			+ "t.flight = f AND t.traveler = tr AND tr.account = :account AND "
			+ "f.departure >= CURRENT_DATE")	
	Set<Ticket> FindAccountUpcomingTickets(@Param("account") Account account);
}

	
