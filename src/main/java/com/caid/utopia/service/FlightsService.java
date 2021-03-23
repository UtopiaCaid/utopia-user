package com.caid.utopia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.caid.utopia.entity.Flights;
import com.caid.utopia.repo.FlightsRepo;

import exception.RecordNotFoundException;


@Service
public class FlightsService {
	
	@Autowired
	FlightsRepo FlightsRepo;
	
	public List<Flights> getAllFlights() {
		
		List<Flights> flights = new ArrayList<>();
		try {
			flights = FlightsRepo.findAll();
			return flights;
		} catch (Exception e) {
			throw new RecordNotFoundException();
		}
		
	}
	
}
