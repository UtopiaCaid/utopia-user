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
	/* create record */
	@Transactional
	@RequestMapping(value = "/Aircraft", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> createAircraft(@RequestBody Aircraft aircraft) throws Exception {
		try {
			if(aircraftService.createAircraft(aircraft) instanceof Aircraft) {
				return new ResponseEntity<>(aircraft, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	@Transactional
	@RequestMapping(value = "/AircraftType", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> createAircraftType(@RequestBody AircraftType aircraftType) throws Exception {
		try {
			if(aircraftService.createAircraftType(aircraftType) instanceof AircraftType) {
				return new ResponseEntity<>(aircraftType, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	/* update record */
	@Transactional
	@RequestMapping(value = "/Aircraft", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> updateAircraft(@RequestBody Aircraft aircraft) throws Exception {
		try {
			if(aircraftService.updateAircraft(aircraft) instanceof Aircraft) {
				return new ResponseEntity<>(aircraft, HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	@Transactional
	@RequestMapping(value = "/AircraftType", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> updateAircraftType(@RequestBody AircraftType aircraftType) throws Exception {
		try {
			if(aircraftService.updateAircraftType(aircraftType) instanceof AircraftType) {
				return new ResponseEntity<>(aircraftType, HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	/* Delete Aircraft */
	@Transactional
	@RequestMapping(value = "/Aircraft", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> deleteAircraft(@RequestBody Aircraft aircraft) throws Exception {
		try {
			aircraftService.deleteAircraft(aircraft);
			return new ResponseEntity<>(aircraft, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	/* Delete Aircraft Type */
	@Transactional
	@RequestMapping(value = "/AircraftType", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> deleteAircraftType(@RequestBody AircraftType aircraftType) throws Exception {
		try {
			aircraftService.deleteAircraftType(aircraftType);
			return new ResponseEntity<>(aircraftType, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	/* Activate/Deactivate Aircraft */
	@Transactional
	@RequestMapping(value = "/Aircraft/Deactivate", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> deactivateAircraft(@RequestBody Aircraft aircraft) throws Exception {
		try {
			if(aircraftService.deactivateAircraft(aircraft) instanceof Aircraft) {
				return new ResponseEntity<>(aircraft, HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	@Transactional
	@RequestMapping(value = "/Aircraft/Activate", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> activateAircraft(@RequestBody Aircraft aircraft) throws Exception {
		try {
			if(aircraftService.activateAircraft(aircraft) instanceof Aircraft) {
				return new ResponseEntity<>(aircraft, HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
}