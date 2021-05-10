package com.caid.utopia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.caid.utopia.entity.Flight;
import com.caid.utopia.entity.Ticket;
import com.caid.utopia.repo.FlightRepo;
import com.caid.utopia.repo.TicketRepo;

import exception.FlightByIdException;
import exception.FlightCreationException;
import exception.FlightDeletionException;
import exception.FlightDetailsException;
import exception.RecordHasDependenciesException;
import exception.RecordNotFoundException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Service
public class FlightService {
	
	@Autowired
	FlightRepo FlightRepo;
	
	@Autowired
	TicketRepo TicketRepo;
	
	public List<Flight> getAllFlight() throws RecordNotFoundException {
		
		try {
			List<Flight> flights = FlightRepo.findAll();
			return flights;
		} catch (Exception e) {
			throw new RecordNotFoundException();
		}		
	}
	
	public Flight getFlightById(Integer id) throws FlightByIdException {
		try {
			Optional<Flight> possibleFlight = FlightRepo.findById(id);
			if(possibleFlight.isPresent()) {
				return possibleFlight.get();
			} else {
				throw new FlightByIdException();
			}
		}catch(Exception e) {
			throw new FlightByIdException();
		}
	}
}
