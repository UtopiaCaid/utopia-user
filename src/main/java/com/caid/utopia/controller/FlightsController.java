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

import exception.FlightByIdException;
import exception.FlightCreationException;
import exception.FlightDeletionException;
import exception.FlightDetailsException;
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
	
	@ExceptionHandler(FlightByIdException.class)
	@RequestMapping(value = "/flights/{flightId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Flights> getFlights(@PathVariable Integer flightId){
		Flights flights = flightsService.getFlightById(flightId);
		return new ResponseEntity<>(flights, HttpStatus.OK);
	}
	
	@ExceptionHandler(FlightCreationException.class)
	@RequestMapping(value = "/flights", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Flights> flightInsertion(@RequestBody Flights newFlights) {
		Flights updatedFlights = flightsService.addFlight(newFlights);
		if (updatedFlights.getFlightNo() == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(newFlights, HttpStatus.OK);
		}			
	}
	
	@ExceptionHandler(FlightDeletionException.class)
	@RequestMapping(value = "/flights", method = RequestMethod.DELETE, consumes = "application/json")
	public ResponseEntity<Flights> flightDeletion(@RequestBody Flights flights) {
		if(flights.getFlightNo() == null) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		List <Flights> updatedFlights = flightsService.deleteFlight(flights);
		if (updatedFlights.contains(flights.getFlightNo())){
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		} else {
			return new ResponseEntity<>(flights, HttpStatus.OK);
		}
	}
	
	@ExceptionHandler(FlightDetailsException.class)
	@RequestMapping(value = "/flights", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public ResponseEntity<List<Flights>> flightDetailsUpdate(@RequestBody Flights flights) {
		List <Flights> updatedFlights = flightsService.updateFlight(flights);
		if(updatedFlights.size() == 0) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
}
