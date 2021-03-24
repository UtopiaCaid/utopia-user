package com.caid.utopia.controller;

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

import com.caid.utopia.entity.Aircraft;
import com.caid.utopia.service.AircraftService;

import exception.RecordNotFoundException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AircraftController {

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
	AircraftService aircraftService;
	
	/* get all records*/
	@RequestMapping(value = "/Aircraft", method = RequestMethod.GET, produces = "application/json")
	public List<Aircraft> getAllFlights(){
		return aircraftService.getAllAircraft();
	}
	
	@RequestMapping(value = "/AircraftType", method = RequestMethod.GET, produces = "application/json")
	public List<Aircraft> getAllAircraftFlights(){
		return aircraftService.getAllAircraft();
	}
	
	/* get record by id */
	@RequestMapping(value = "/getAircraft{id}", method = RequestMethod.GET, produces = "application/json")
	public Aircraft getAircraftById(@RequestBody Integer id){
		return aircraftService.getAircraftById(id);
	}
	
	@RequestMapping(value = "/getAircraftType{Id}", method = RequestMethod.GET, produces = "application/json")
	public Aircraft getAircraftTypeById(@RequestBody Integer id){
		return aircraftService.getAircraftById(id);
	}
	
	/* create record */
	
	
}
