package com.caid.utopia.service;

	import java.util.List;
	import java.util.Optional;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

import com.caid.utopia.entity.Airport;
import com.caid.utopia.entity.Flight;
import com.caid.utopia.entity.Ticket;
import com.caid.utopia.repo.AirportRepo;
import com.caid.utopia.repo.FlightRepo;

import exception.RecordAlreadyExistsException;
import exception.RecordCreationException;
import exception.RecordForeignKeyConstraintException;
import exception.RecordHasDependenciesException;
import exception.RecordNotFoundException;
import exception.RecordUpdateException;


	@Service
	public class AirportService {
		
		@Autowired
		FlightRepo FlightsRepo;
		
		@Autowired
		FlightRepo flightRepo;
		
		@Autowired
		AirportRepo airportRepo;
		
		/* Create airport type */
		//public Airport createAirportType() throws 
		
		/* Read all airport */
		public List<Airport> getAllAirports() throws RecordNotFoundException {
			
			try {
				List<Airport> airport = airportRepo.findAll();
				return airport;
			} catch (Exception e) {
				throw new RecordNotFoundException();
			}		
		}
		
		/* Read airport by id */
		public Airport getAirportById(Integer id) throws RecordNotFoundException {
			try {
				Optional<Airport> airport = airportRepo.findById(id);
				if(airport.isPresent()) {
					return airport.get();
				} else {
					throw new RecordNotFoundException();
				}
			}catch(Exception e) {
				throw new RecordNotFoundException();
			}
		}
	}