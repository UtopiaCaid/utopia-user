package com.caid.utopia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

<<<<<<< HEAD:src/main/java/com/caid/utopia/service/FlightsService.java
import com.caid.utopia.entity.Aircraft;
import com.caid.utopia.entity.Flights;
import com.caid.utopia.repo.FlightsRepo;
=======
import com.caid.utopia.entity.Flight;
import com.caid.utopia.repo.FlightRepo;
>>>>>>> development:src/main/java/com/caid/utopia/service/FlightService.java

import exception.FlightByIdException;
import exception.FlightCreationException;
import exception.RecordNotFoundException;
import exception.updateFlightPlaneException;
import exception.FlightDeletionException;
import exception.FlightDetailsException;
import io.micrometer.core.ipc.http.HttpSender.Response;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Service
public class FlightService {
	
	@Autowired
	FlightRepo FlightRepo;
	
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
	
<<<<<<< HEAD:src/main/java/com/caid/utopia/service/FlightsService.java
	public Flights addFlight(Flights flights) throws FlightCreationException {
		try {
			 FlightsRepo.save(flights);
			 return flights;
=======
	public List<Flight> addFlight(Flight flights) throws FlightCreationException {
		try {
			 FlightRepo.save(flights);
			 return FlightRepo.findAll();
>>>>>>> development:src/main/java/com/caid/utopia/service/FlightService.java
		} catch (Exception e) {
			throw new FlightCreationException();
		}
	}
	
	public List<Flight> deleteFlight(Flight flights) throws FlightDeletionException {
		try {
			FlightRepo.delete(flights);
			return FlightRepo.findAll();
		} catch (Exception e) {
			throw new FlightDeletionException();
		}
	}
	
	public List<Flights> updateFlight(Flights flights) throws FlightDetailsException {
		try {
			if(FlightsRepo.existsById(flights.getFlightNo())) {
				FlightsRepo.save(flights);
				return FlightsRepo.findAll();
			}
			throw new FlightDetailsException();
		} catch (Exception e) {
			throw new FlightDetailsException();
		}
	}
	
	
}
