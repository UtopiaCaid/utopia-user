package com.caid.utopia.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

	@ExceptionHandler(RecordNotFoundException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  public ResponseEntity<String> handleNoSuchElementFoundException(
			  RecordNotFoundException exception
	  ) {
	    return ResponseEntity
	        .status(HttpStatus.NOT_FOUND)
	        .body(exception.getMessage());
	  }
	
	@Autowired
	FlightsService flightsService;
	
	@RequestMapping(value = "/getAllFlights", method = RequestMethod.GET, produces = "application/json")
	public List<Flights> getAllFlights(){
		return flightsService.getAllFlights();
		//return flightsService.getAllFlights();
	}
	
}
