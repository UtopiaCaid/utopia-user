package com.caid.utopia.repo;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.caid.utopia.entity.AccountRole;
import com.caid.utopia.entity.Aircraft;
import com.caid.utopia.entity.Airport;
import com.caid.utopia.entity.Flight;
import com.caid.utopia.entity.Ticket;

@Repository
public interface FlightRepo extends JpaRepository<Flight, Integer>{
	@Query("FROM Flight WHERE aircraft = :curr ")
	List<Flight> AircraftHasFlights(@Param("curr") Aircraft curr);
	
	@Query("FROM Flight WHERE airportArrival = :curr OR airportDeparture = :curr")
	List<Flight> AirportHasFlights(@Param("curr") Airport airport);
}
