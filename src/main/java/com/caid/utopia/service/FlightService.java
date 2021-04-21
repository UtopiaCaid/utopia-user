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
<<<<<<< HEAD
import exception.RecordHasDependenciesException;
=======
>>>>>>> development
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
	

	public Flight addFlight(Flight flight) throws FlightCreationException {
		try {
			 FlightRepo.save(flight);
			 return flight;
		} catch (Exception e) {
			throw new FlightCreationException();
		}
	}
	
	public List<Flight> deleteFlight(Flight flights) throws FlightDeletionException {
		try {
			List<Ticket> tickets = TicketRepo.FlightHasTickets(flights);
			if(tickets.size() > 0) {
				throw new RecordHasDependenciesException();
			}
			FlightRepo.delete(flights);
			return FlightRepo.findAll();
		} catch (Exception e) {
			throw new FlightDeletionException();
		}
	}
	
	public List<Flight> updateFlight(Flight flights) throws FlightDetailsException {
		try {
			if(FlightRepo.existsById(flights.getFlightNo())) {
				FlightRepo.save(flights);
				return FlightRepo.findAll();
			}
			throw new FlightDetailsException();
		} catch (Exception e) {
			throw new FlightDetailsException();
		}
	}
	
	
<<<<<<< HEAD
}
=======
}
>>>>>>> development
