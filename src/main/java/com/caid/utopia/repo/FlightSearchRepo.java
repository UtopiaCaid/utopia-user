package com.caid.utopia.repo;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.caid.utopia.entity.Airport;
import com.caid.utopia.entity.Flight;

@Repository
public interface FlightSearchRepo extends JpaRepository<Flight, Integer>{

	

	/* find all flights that depart from initial airport within a date window */
	@Query("FROM Flight WHERE "
			+ "airportDeparture = :airportDep AND airportArrival != :airportArr "
			+ "AND departure >= :flightDepBeginDate AND departure <= :flightDepEndDate "
			+ "AND status = 'Ready'")
	List<Flight> FindInitialLayover(
			@Param("airportDep") Airport aiportDep, @Param("airportArr") Airport airportArr,  
			@Param("flightDepBeginDate") LocalDate flightDepBeginDate,
			@Param("flightDepEndDate") LocalDate flightDepEndDate
			);
	
	/* find all layover flights within a date window */
	@Query("FROM Flight WHERE "
			+ "airportDeparture = :airportCurr AND airportArrival = :airportDest "
			+ "AND departure >= :flightArrival "
			+ "AND status = 'Ready'")
	/* airport B, airport C, flight A->B arrival date */
	List<Flight> FindLayover(
			@Param("airportCurr") Airport aiportCurr, @Param("airportDest") Airport airportDest,  
			@Param("flightArrival") LocalDate flightArrival
			);
	

	/* find one way non-layover flights within the time window */
	@Query("FROM Flight WHERE "
			+ "airportDeparture = :airportDep AND airportArrival = :airportArr "
			+ "AND departure >= :flightDepBeginDate AND departure <= :flightDepEndDate "
			+ "AND status = 'Ready'")
	List<Flight> FindOneWayNoLayover(
			@Param("airportDep") Airport aiportDep, @Param("airportArr") Airport airportArr,  
			@Param("flightDepBeginDate") LocalDate flightDepBeginDate,
			@Param("flightDepEndDate") LocalDate flightDepEndDate
			);
	
	/* find one way layover flights initial flight */
	@Query("FROM Flight WHERE "
			+ "airportDeparture = :airportDep "
			+ "AND departure >= :flightDepBeginDate AND departure <= :flightDepEndDate "
			+ "AND status = 'Ready'")
	List<Flight> FindOneWayLayoverInit(
			@Param("airportDep") Airport aiportDep,  
			@Param("flightDepBeginDate") LocalDate flightDepBeginDate,
			@Param("flightDepEndDate") LocalDate flightDepEndDate
			);
	
	/* find one way layover flights non-initia flight */
	@Query("FROM Flight WHERE "
			+ "airportDeparture = :airportDep "
			+ "AND departure >= :initFlightArr "
			+ "AND status = 'Ready'")
	ArrayList<Flight> FindOneWayLayoverCont(
			@Param("airportDep") Airport aiportDep,  
			@Param("initFlightArr") LocalDate initFlightArr
			);
	
	/* find round trip non-layover flights within the time window */ 
	
	@Query("FROM Flight WHERE "
			+ "airportDeparture = :airportDep AND airportArrival = :airportArr "
			+ "AND departure >= :flightDepBeginDate AND departure <= :flightDepEndDate "
			+ "AND status = 'Ready' ")
	List<Flight> FindRoundTripDepartureNoLayover(
			@Param("airportDep") Airport aiportDep, @Param("airportArr") Airport airportArr,  
			@Param("flightDepBeginDate") LocalDate flightDepBeginDate,
			@Param("flightDepEndDate") LocalDate flightDepEndDate
			);
    
	@Query("FROM Flight WHERE "
			+ "airportDeparture = :airportArr AND airportArrival = :airportDep "
			+ "AND departure >= :flightRetBeginDate AND departure <= :flightRetEndDate "
			+ "AND status = 'Ready' ")
	List<Flight> FindRoundTripReturnNoLayover(
	@Param("airportDep") Airport aiportDep, @Param("airportArr") Airport airportArr,  
	@Param("flightRetBeginDate") LocalDate flightRetBeginDate,
	@Param("flightRetEndDate") LocalDate flightRetEndDate
	);
	
	

	/* find all flights that return from destination airport within a date window */
	@Query("FROM Flight WHERE "
			+ "airportDeparture = :airportDep AND airportArrival != :airportArr "
			+ "AND departure >= :flightArrBeginDate AND departure <= :flightArrEndDate "
			+ "AND status = 'Ready'")
	/* Airport C, Airport B, Flight C->B departure between ret begin and end date */
	List<Flight> FindRoundTripInitialReturnLayover(
			@Param("airportDep") Airport aiportDep, @Param("airportArr") Airport airportArr,  
			@Param("flightArrBeginDate") LocalDate flightArrBeginDate,
			@Param("flightArrEndDate") LocalDate flightArrEndDate
			);
	
	/* find all layover return flights within a date window */
	@Query("FROM Flight WHERE "
			+ "airportDeparture = :airportCurr AND airportArrival = :airportDest "
			+ "AND departure >= :flightArrival "
			+ "AND status = 'Ready'")
	/* airport B, airport A, flight ->A arrival date */
	List<Flight> FindRoundTripReturnLayover(
			@Param("airportCurr") Airport aiportCurr, @Param("airportDest") Airport airportDest,  
			@Param("flightArrival") LocalDate flightArrival
			);
	
}
