package com.caid.utopia.controller;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import com.caid.utopia.entity.Flights;
import com.caid.utopia.service.FlightsService;

import exception.RecordNotFoundException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FlightsController {
	
	public static final String PAGE_NOT_FOUND_LOG_CATEGORY = "org.springframework.web.servlet.PageNotFound";

	@Autowired
	FlightsService flightsService;
	
	@ExceptionHandler(
			RecordNotFoundException.class
	)
	@Nullable
	public final ResponseEntity<Object> handleException(Exception ex, WebRequest request) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		if (ex instanceof RecordNotFoundException) {
			HttpStatus status = HttpStatus.FOUND;
			return handleHttpRequestRecordNotFound(
					(RecordNotFoundException) ex, headers, status, request);
		}
		throw ex;
	}
	
	protected static final Log pageNotFoundLogger = LogFactory.getLog(PAGE_NOT_FOUND_LOG_CATEGORY);

	protected ResponseEntity<Object> handleHttpRequestRecordNotFound(
			RecordNotFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		pageNotFoundLogger.warn(ex.getMessage());
		return handleExceptionInternal(ex, null, headers, status, request);
	}		

	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}
		return new ResponseEntity<>(body, headers, status);
	}
	
	@RequestMapping(value = "/flights", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Flights>> getAllFlights(){
		List<Flights> flights = flightsService.getAllFlights();
		if (flights.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(flights, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value = "/flights/{flightId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Flights> getAllFlights(@PathVariable Integer flightId){
		Flights flights = flightsService.getFlightById(flightId);
		return new ResponseEntity<>(flights, HttpStatus.OK);

		
	}
	
}