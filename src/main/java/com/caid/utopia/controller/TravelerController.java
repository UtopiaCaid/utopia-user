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
import com.caid.utopia.entity.Traveler;
import com.caid.utopia.service.TravelerService;
import com.caid.utopia.service.FlightsService;

import exception.RecordNotFoundException;
import exception.RecordCreationException;
import exception.RecordForeignKeyConstraintException;
import exception.ExceptionReducer;
import exception.RecordAlreadyExistsException;
import exception.RecordUpdateException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TravelerController {
	

	@Autowired
	FlightsService flightsService;
	
	@ExceptionHandler({
		RecordNotFoundException.class, //404
		RecordCreationException.class, //404
		RecordForeignKeyConstraintException.class, //409
		RecordAlreadyExistsException.class, //409
		RecordUpdateException.class, //400
	})
	@Nullable
	public final ResponseEntity<Object> handleException(Exception ex) throws Exception {
		return ExceptionReducer.handleException(ex);
	}
	
	@Autowired
	TravelerService travelerService;
	
	/* get all records*/
	@RequestMapping(value = "/Traveler", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Traveler>> getAllTraveler(){
		List<Traveler> traveler = travelerService.getAllTraveler();
		if( traveler.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(traveler, HttpStatus.OK);
		}
	}
	
	
	/* get record by id */
	@RequestMapping(value = "/Traveler/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Traveler> getTravelerById(@PathVariable Integer id){
		Traveler traveler = travelerService.getTravelerById(id);
		if(traveler.getTravelerId() != id) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(traveler, HttpStatus.OK);
		}	
	}
	
	
	/* create record */
	@Transactional
	@RequestMapping(value = "/Traveler", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> createTraveler(@RequestBody Traveler traveler) throws Exception {
		try {
			if(travelerService.createTraveler(traveler) instanceof Traveler) {
				return new ResponseEntity<>(traveler, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	/* update record */
	@Transactional
	@RequestMapping(value = "/Traveler", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> updateTraveler(@RequestBody Traveler traveler) throws Exception {
		try {
			if(travelerService.updateTraveler(traveler) instanceof Traveler) {
				return new ResponseEntity<>(traveler, HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}
		
}