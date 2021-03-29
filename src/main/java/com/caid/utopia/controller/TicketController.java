package com.caid.utopia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import com.caid.utopia.service.AircraftService;
import com.caid.utopia.service.FlightsService;

import exception.RecordNotFoundException;
import exception.RecordCreationException;
import exception.RecordForeignKeyConstraintException;
import exception.RecordAlreadyExistsException;
import exception.RecordUpdateException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TicketController {
	/*

	@Autowired
	FlightsService flightsService;
	
	@ExceptionHandler({
		RecordNotFoundException.class,
		RecordCreationException.class,
		RecordForeignKeyConstraintException.class,
		RecordAlreadyExistsException.class,
		RecordUpdateException.class,
	})
	@Nullable
	*/
}