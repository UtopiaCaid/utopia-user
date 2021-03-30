package com.caid.utopia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.caid.utopia.entity.Flight;
import com.caid.utopia.repo.FlightRepo;

import exception.FlightByIdException;
import exception.FlightCreationException;
import exception.RecordNotFoundException;
import exception.FlightDeletionException;
import io.micrometer.core.ipc.http.HttpSender.Response;


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
	
	public List<Flight> addFlight(Flight flights) throws FlightCreationException {
		try {
			 FlightRepo.save(flights);
			 return FlightRepo.findAll();
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
	
}
