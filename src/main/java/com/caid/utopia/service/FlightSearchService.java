	package com.caid.utopia.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caid.utopia.entity.Flight;
import com.caid.utopia.repo.AircraftRepo;
import com.caid.utopia.repo.AircraftTypeRepo;
import com.caid.utopia.repo.AirportRepo;
import com.caid.utopia.repo.FlightRepo;
import com.caid.utopia.repo.FlightSearchRepo;

import exception.RecordNotFoundException;


	@Service
	public class FlightSearchService {
		
		@Autowired
		FlightSearchRepo flightSearchRepo;
		
		@Autowired
		FlightRepo flightRepo;
		
		@Autowired
		AircraftRepo aircraftRepo;
		
		@Autowired
		AircraftTypeRepo aircraftTypeRepo;
		
		@Autowired
		AirportRepo airportRepo;
		
		
		/* Find Flights from a specific airport */
		public List<Flight> FindOneWayNoLayover(
				Integer airportDep, Integer airportArr,
				LocalDate flightDepBeginDate, LocalDate flightDepEndDate
				) throws RecordNotFoundException {
			try {
				return flightSearchRepo.FindOneWayNoLayover(
						airportRepo.findById(airportDep).get(), airportRepo.findById(airportArr).get(), 
						flightDepBeginDate, flightDepEndDate);
			} catch (Exception e) {
				throw new RecordNotFoundException();
			}
		}
		
		/* Find all combinations of A-B-C layover flights */
		public List<ArrayList<Flight>> FindOneWayLayover(
				Integer airportDep, Integer airportArr,
				LocalDate flightDepBeginDate, LocalDate flightDepEndDate) {
			try {
				/* Start from Aiport A */
				List<Flight> StartFlights = flightSearchRepo.FindInitialLayover(
						airportRepo.findById(airportDep).get(), airportRepo.findById(airportArr).get(), 
						flightDepBeginDate, flightDepEndDate);
				if(StartFlights.isEmpty()) {
					return null;
				}
				/* Store flight routes */
				List<ArrayList<Flight>> FlightRoutes = new ArrayList<ArrayList<Flight>>();
				/* Iterate through all connections from Airport B to C */
				ListIterator<Flight> it = StartFlights.listIterator();
				while(it.hasNext()) {
					Flight curr = it.next();
					/* args -> airport B, airport C, flight a->b arrival time */
					List<Flight> LayoverFlights = flightSearchRepo.FindLayover(curr.getairportArrival(), 
							airportRepo.findById(airportArr).get(), curr.getArrival());
					if(!LayoverFlights.isEmpty()) {
						ListIterator<Flight> it2 = LayoverFlights.listIterator();
						while (it2.hasNext()) {
							ArrayList<Flight> route = new ArrayList<Flight>(2);
							route.add(0, curr);
							route.add(1, it2.next());
							FlightRoutes.add(route);
						}
					}
					
				}
				return FlightRoutes;
			} catch (Exception e) {
				throw new RecordNotFoundException();
			}

		}
		
		/* Find round trip departure flights no layovers*/
		public List<Flight> FindRoundTripDepartureNoLayover(
				Integer airportDep, Integer airportArr,
				LocalDate flightDepBeginDate, LocalDate flightDepEndDate
				) throws RecordNotFoundException {
			try {
				return flightSearchRepo.FindRoundTripDepartureNoLayover(
						airportRepo.findById(airportDep).get(), airportRepo.findById(airportArr).get(), 
						flightDepBeginDate, flightDepEndDate);
			} catch (Exception e) {
				throw new RecordNotFoundException();
			}
		}
		
		/* Find round trip return flights no layovers*/
		public List<Flight> FindRoundTripReturnNoLayover(
				Integer airportDep, Integer airportArr,
				LocalDate flightRetBeginDate, LocalDate flightRetEndDate
				) throws RecordNotFoundException {
			try {
				return flightSearchRepo.FindRoundTripReturnNoLayover(
						airportRepo.findById(airportDep).get(), airportRepo.findById(airportArr).get(), 
						flightRetBeginDate, flightRetEndDate);
			} catch (Exception e) {
				throw new RecordNotFoundException();
			}
		}
	}