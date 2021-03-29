package com.caid.utopia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caid.utopia.entity.Flight;
import com.caid.utopia.repo.FlightRepo;

import exception.RecordNotFoundException;


@Service
public class FlightsService {
	
	@Autowired
	FlightRepo FlightsRepo;
	
	public List<Flight> getAllFlights() throws RecordNotFoundException {
		
		try {
			List<Flight> flights = FlightsRepo.findAll();
			return flights;
		} catch (Exception e) {
			throw new RecordNotFoundException();
		}		
	}
	
	public Flight getFlightById(Integer id) throws RecordNotFoundException {
		try {
			Optional<Flight> possibleFlight = FlightsRepo.findById(id);
			if(possibleFlight.isPresent()) {
				return possibleFlight.get();
			} else {
				throw new RecordNotFoundException();
			}
		}catch(Exception e) {
			throw new RecordNotFoundException();
		}
	}
	
}