package com.caid.utopia.repo;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.caid.utopia.entity.Account;
import com.caid.utopia.entity.Aircraft;
import com.caid.utopia.entity.Airport;
import com.caid.utopia.entity.Flight;
import com.caid.utopia.entity.Traveler;

@Repository
public interface FlightRepo extends JpaRepository<Flight, Integer>{
	@Query("FROM Flight WHERE aircraft = :curr ")
	List<Flight> AircraftHasFlights(@Param("curr") Aircraft curr);
	
	@Query("FROM Flight WHERE airportArrival = :curr OR airportDeparture = :curr")
	List<Flight> AirportHasFlights(@Param("curr") Airport airport);
	
	@Query("FROM Flight f "
			+ "JOIN f.tickets t "
			+ "JOIN t.traveler tr WHERE "
			+ "t.flight = f AND t.traveler = tr AND tr.account = :account")
	Set<Flight> FindAccountFlightHistory(@Param("account") Account account);
	
	@Query("FROM Flight f "
			+ "JOIN f.tickets t "
			+ "JOIN t.traveler tr WHERE "
			+ "t.flight = f AND t.traveler = tr AND tr.account = :account AND "
			+ "f.departure >= CURRENT_DATE")	
	Set<Flight> FindAccountUpcomingFlights(@Param("account") Account account);
}
