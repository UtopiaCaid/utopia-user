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
		public List<ArrayList<Flight>> FindOneWaySingleLayover(
				Integer airportDep, Integer airportArr,
				LocalDate flightDepBeginDate, LocalDate flightDepEndDate) {
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
				LocalDate flightDepBeginDate, LocalDate flightDepEndDate) {
			try {
				/* temp max layovers */
				Integer maxLayovers = 3;
				/* Start from Airport A */
				List<Flight> StartFlights = flightSearchRepo.FindOneWayLayoverInit(
						airportRepo.findById(airportStart).get(), 
						flightDepBeginDate, flightDepEndDate);
				if(StartFlights.isEmpty()) {
					return null;
				}
				/* Store flight routes */
				List<ArrayList<Flight>> currentFlightRoutes = new ArrayList<ArrayList<Flight>>();
				List<ArrayList<Flight>> FlightRoutes = new ArrayList<ArrayList<Flight>>();
				/* Iterate through all connections from Airport B to C */
				for(int i = 0; i < maxLayovers; i++) {
					/* Put starting flights into the 2d list */
					if(i == 0) {
						ListIterator<Flight> init = StartFlights.listIterator();
						while(init.hasNext()) {
							ArrayList<Flight> temp = new ArrayList<>();
							temp.add(init.next());
							currentFlightRoutes.add(temp);
						}
					/* add layover flights if applicable */
					} else {
						List<ArrayList<Flight>> cloneTemp = new ArrayList<ArrayList<Flight>>(currentFlightRoutes);
						ListIterator<ArrayList<Flight>> it = cloneTemp.listIterator();
						while(it.hasNext()) {
							currentFlightRoutes.clear();
							ArrayList<Flight> temp = it.next();
							/* if the current flight airport is the destination */
							if(temp.get(temp.size()-1).getairportArrival().getAirportId() != airportEnd) {
								ArrayList<Flight> layovers = flightSearchRepo.FindOneWayLayoverCont(
									temp.get(temp.size()-1).getairportArrival(),
									temp.get(temp.size()-1).getArrival());
								/* add new flight routes to currentFlightRoutes */
								ListIterator<Flight > it2 = layovers.listIterator();
								while(it2.hasNext()) {
									ArrayList<Flight> temp2 = new ArrayList<Flight>(temp);
									temp2.add(it2.next());
									currentFlightRoutes.add(temp2);
								}
							} else {
								FlightRoutes.add(temp);
							}
						}
					}
					if(i == maxLayovers-1) {
						ListIterator<ArrayList<Flight>> it = currentFlightRoutes.listIterator();
						while(it.hasNext()) {
							ArrayList<Flight> temp = it.next();
							if (temp.get(temp.size()-1).getairportArrival().getAirportId() == airportEnd)
							{ FlightRoutes.add(temp); }
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