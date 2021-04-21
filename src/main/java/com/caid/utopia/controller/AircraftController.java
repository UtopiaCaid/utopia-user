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
import com.caid.utopia.entity.Aircraft;
import com.caid.utopia.entity.AircraftType;
import com.caid.utopia.entity.Ticket;
import com.caid.utopia.repo.FlightRepo;
import com.caid.utopia.service.AircraftService;
import com.caid.utopia.service.FlightService;

import exception.RecordNotFoundException;
import exception.RecordCreationException;
import exception.RecordForeignKeyConstraintException;
import exception.ExceptionReducer;
import exception.RecordAlreadyExistsException;
import exception.RecordUpdateException;
import exception.RecordHasDependenciesException;
import exception.RecordHasDependenciesException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user")
@RestController
public class AircraftController {
	

	@Autowired
	FlightService flightsService;
	
	@ExceptionHandler({
		RecordNotFoundException.class, //404
		RecordCreationException.class, //404
		RecordForeignKeyConstraintException.class, //409
		RecordAlreadyExistsException.class, //409
		RecordUpdateException.class, //400
		RecordHasDependenciesException.class, //422
	})
	@Nullable
	public final ResponseEntity<Object> handleException(Exception ex) throws Exception {
		return ExceptionReducer.handleException(ex);
	}
	
	@Autowired
	AircraftService aircraftService;
	
	/* get all records*/
	@RequestMapping(value = "/Aircraft", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Aircraft>> getAllAircraft(){
		List<Aircraft> aircraft = aircraftService.getAllAircraft();
		if( aircraft.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(aircraft, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/AircraftType", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<AircraftType>> getAllAircraftTypes(){
		List<AircraftType> aircraftTypes = aircraftService.getAllAircraftTypes();
		if( aircraftTypes.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(aircraftTypes, HttpStatus.OK);
		}
	}
	
	/* get record by id */
	@RequestMapping(value = "/Aircraft/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Aircraft> getAircraftById(@PathVariable Integer id){
		Aircraft aircraft = aircraftService.getAircraftById(id);
		if(aircraft.getAircraftId() != id) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(aircraft, HttpStatus.OK);
		}	
	}
	
	
	@RequestMapping(value = "/AircraftType/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<AircraftType> getAircraftTypeById(@PathVariable Integer id){
		AircraftType aircraftType = aircraftService.getAircraftTypeById(id);
		if(aircraftType.getAircraftTypeId() != id) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(aircraftType, HttpStatus.OK);
		}	
	}
}