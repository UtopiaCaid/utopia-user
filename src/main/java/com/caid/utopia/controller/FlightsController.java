package com.caid.utopia.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.caid.utopia.entity.Flights;
import com.caid.utopia.service.FlightsService;

import exception.RecordNotFoundException;
import io.micrometer.core.ipc.http.HttpSender.Response;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FlightsController {

	
	
	
	@Autowired
	FlightsService flightsService;
	
	@ExceptionHandler(RecordNotFoundException.class)
	@RequestMapping(value = "/flights", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Flights>> getAllFlights(){
		List<Flights> flights = flightsService.getAllFlights();
		if (flights.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(flights, HttpStatus.OK);
		}
		
	}
	
//	@ExceptionHandler(RecordNotFoundException.class)
	@RequestMapping(value = "/flights/{flightId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Flights> getAllFlights(@PathVariable Integer flightId){
		Flights flights = flightsService.getFlightById(flightId);
		return new ResponseEntity<>(flights, HttpStatus.OK);

		
	}
	
}
