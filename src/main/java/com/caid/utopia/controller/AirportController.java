package com.caid.utopia.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caid.utopia.entity.Flight;
import com.caid.utopia.entity.Airport;
import com.caid.utopia.service.AirportService;
import exception.RecordNotFoundException;
import exception.RecordCreationException;
import exception.RecordForeignKeyConstraintException;
import exception.ExceptionReducer;
import exception.RecordAlreadyExistsException;
import exception.RecordUpdateException;
import exception.RecordHasDependenciesException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user")
@RestController
public class AirportController {
	

	@Autowired
	AirportService airportService;
	
	@ExceptionHandler({
		RecordNotFoundException.class, //404
		RecordCreationException.class, //404
		RecordForeignKeyConstraintException.class, //409
		RecordAlreadyExistsException.class, //409
		RecordUpdateException.class, //400
		RecordHasDependenciesException.class //422
	})
	@Nullable
	public final ResponseEntity<Object> handleException(Exception ex) throws Exception {
		return ExceptionReducer.handleException(ex);
	}
	
	
	/* get all records*/
	@RequestMapping(value = "/Airport", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Airport>> getAllAirport(){
		List<Airport> airport = airportService.getAllAirports();
		System.out.println(airport.size());
		if( airport.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(airport, HttpStatus.OK);
		}
	}
	
	/* get record by id */
	@RequestMapping(value = "/Airport/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Airport> getAirportById(@PathVariable Integer id){
		Airport airport = airportService.getAirportById(id);
		if(airport.getAirportId() != id) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(airport, HttpStatus.OK);
		}	
	}
}