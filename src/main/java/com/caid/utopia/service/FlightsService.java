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

import com.caid.utopia.entity.Aircraft;
import com.caid.utopia.entity.Flights;
import com.caid.utopia.repo.FlightsRepo;

import exception.FlightByIdException;
import exception.FlightCreationException;
import exception.RecordNotFoundException;
import exception.updateFlightPlaneException;
import exception.FlightDeletionException;
import exception.FlightDetailsException;
import io.micrometer.core.ipc.http.HttpSender.Response;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Service
public class FlightsService {
	
	@Autowired
	FlightsRepo FlightsRepo;
	
	public List<Flights> getAllFlights() throws RecordNotFoundException {
		
		try {
			List<Flights> flights = FlightsRepo.findAll();
			return flights;
		} catch (Exception e) {
			throw new RecordNotFoundException();
		}		
	}
	
	public Flights getFlightById(Integer id) throws FlightByIdException {
		try {
			Optional<Flights> possibleFlight = FlightsRepo.findById(id);
			if(possibleFlight.isPresent()) {
				return possibleFlight.get();
			} else {
				throw new FlightByIdException();
			}
		}catch(Exception e) {
			throw new FlightByIdException();
		}
	}
	
	public Flights addFlight(Flights flights) throws FlightCreationException {
		try {
			 FlightsRepo.save(flights);
			 return flights;
		} catch (Exception e) {
			throw new FlightCreationException();
		}
	}
	
	public List<Flights> deleteFlight(Flights flights) throws FlightDeletionException {
		try {
			FlightsRepo.delete(flights);
			return FlightsRepo.findAll();
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
