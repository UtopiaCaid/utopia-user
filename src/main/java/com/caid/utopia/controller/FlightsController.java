package com.caid.utopia.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.caid.utopia.entity.Flights;
import com.caid.utopia.service.FlightsService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FlightsController {

	@Autowired
	FlightsService flightsService;
	
	@RequestMapping(value = "/getAllFlights", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getAllFlights(){
		try {
			List<Flights> flights = flightsService.getAllFlights();
			return new ResponseEntity<>(flights, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to retrieve flights", HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
}
