	package com.caid.utopia.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;
import java.util.stream.Stream;

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
				LocalDateTime flightDepBeginDate, LocalDateTime flightDepEndDate
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
		public List<ArrayList<Flight>> FindOneWaySingleLayover(
				Integer airportDep, Integer airportArr,
				LocalDateTime flightDepBeginDate, LocalDateTime flightDepEndDate) {
			try {
				/* Start from Airport A */
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
		
		public List<ArrayList<Flight>> FindAllOneWayFlights(
				Integer airportStart, Integer airportEnd,
				LocalDateTime flightDepBeginDate, LocalDateTime flightDepEndDate) {
			try {
				/* temp max layovers */
				Integer maxLayovers = 3;
				/* Store flight routes */
				List<ArrayList<Flight>> FinalFlightRoutes = new ArrayList<ArrayList<Flight>>();
				final List<ArrayList<Flight>> FlightRoutes = new ArrayList<>();
				/* Iterate through all connections from Airport B to C */
				for(int i = 0; i < maxLayovers; i++) {
					/* Put starting flights into the 2d list */
					if(i == 0) {
						flightSearchRepo.FindOneWayLayoverInit(
								airportRepo.findById(airportStart).get(),
								flightDepBeginDate,
								flightDepEndDate)
								.stream().forEach(flight -> {
									if(flight.getairportArrival().getAirportId() == airportEnd) {
										FinalFlightRoutes.add(new ArrayList<Flight>(Arrays.asList(flight)));
									} else {
										FlightRoutes.add(new ArrayList<Flight>(Arrays.asList(flight)));
									}
								});
					} else {
						/* ignore all flight routes that have a size less than the current index */
						final int size = i;
						List<ArrayList<Flight>> tempFlightRoutes = new ArrayList<>();
						FlightRoutes.stream()
						.filter(flightCheck -> flightCheck.size() == size)
						.forEach(flightRoute -> {
							Flight CurrentFlight = flightRoute.get(flightRoute.size() - 1);
							/* layover intermission should be 2 days max */
							LocalDateTime duration = CurrentFlight.getArrival().plusDays(2);
							flightSearchRepo.FindConnectingFlights(
									CurrentFlight.getairportArrival(),
									CurrentFlight.getArrival(),
									duration).stream().forEach(flight -> {
										ArrayList<Flight> temp = new ArrayList<Flight>(flightRoute);
										temp.add(flight);
										if(flight.getairportArrival().getAirportId() == airportEnd) {
											FinalFlightRoutes.add(temp);
										} else {
											tempFlightRoutes.add(new ArrayList<Flight>(Arrays.asList(flight)));
										}
									});

						});
						FlightRoutes.clear();
						FlightRoutes.addAll(tempFlightRoutes);
					}
				}
				return FinalFlightRoutes;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RecordNotFoundException();
			}

		}
		
		/* Find round trip departure flights no layovers*/
		public List<Flight> FindRoundTripDepartureNoLayover(
				Integer airportDep, Integer airportArr,
				LocalDateTime flightDepBeginDate, LocalDateTime flightDepEndDate
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
				LocalDateTime flightRetBeginDate, LocalDateTime flightRetEndDate
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