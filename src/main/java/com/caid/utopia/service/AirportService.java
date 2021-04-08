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
		
		/* Create Airport */
		public Airport createAirport(Airport airport) throws RecordCreationException {
			try {
				/* Check field values */
				Integer airportCode = airport.getAirportCode();
				String city = airport.getCity();
				String airportName = airport.getAirportName();
				String status = airport.getStatus();

				if(airportCode == null || airportCode < 0
					|| city == null || city.length() <= 0 || city.length() > 45
					|| airportName == null || airportName.length() <= 0 || airportName.length() > 45
					|| status == null || status.length() <= 0 || status.length() > 45) {
					throw new RecordCreationException();
				}
				return airportRepo.save(airport);
			}catch(Exception e) {
				throw e;
			}
		}
		
		/* Update Airport */
		public Airport updateAirport(Airport airport) throws RecordUpdateException {
			try {
				if(airportRepo.findById(airport.getAirportId()).isEmpty()) {
					throw new RecordUpdateException();
				}
				Airport updatedAirport = airportRepo.findById(airport.getAirportId()).get();
				Integer airportCode = airport.getAirportCode();
				if(airportCode > 0) {
					updatedAirport.setAirportCode(airportCode);
				}
				String city = airport.getCity();
				if(city != null && city.length() > 0 && city.length() <= 45) {
					updatedAirport.setCity(city);
				}
				String airportName = airport.getAirportName	();
				if(airportName != null && airportName.length() > 0 && airportName.length() <= 45) {
					updatedAirport.setAirportName(airportName);
				}
				String status = airport.getStatus();
				if(status != null && status.length() > 0 && status.length() <= 45) {
					updatedAirport.setStatus(status);
				}
				return airportRepo.save(updatedAirport);
			}catch(Exception e) {
				throw e;
			}
		}
		
		/* Delete Airport */
		public void deleteAirport(Airport airport) throws RecordUpdateException {
			try {
				Optional<Airport> temp = airportRepo.findById(airport.getAirportId());
				if(temp.isEmpty()) {
					throw new RecordNotFoundException();
				}
				airport = temp.get();
				if(flightRepo.AirportHasFlights(airport).isEmpty()) {
					airportRepo.delete(airport);
				} else {
					throw new RecordHasDependenciesException();
				}
			}catch(Exception e) {
				throw e;
			}
		}
	}