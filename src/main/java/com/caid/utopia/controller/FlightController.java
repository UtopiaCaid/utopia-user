package com.caid.utopia.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import com.caid.utopia.entity.Flight;
import com.caid.utopia.entity.flightSearch.OneWayBody;
import com.caid.utopia.entity.flightSearch.RoundTripBody;
import com.caid.utopia.entity.flightSearch.RoundTripBody;
import com.caid.utopia.service.FlightSearchService;
import com.caid.utopia.service.FlightService;

import exception.FlightByIdException;
import exception.FlightCreationException;
import exception.FlightDeletionException;
import exception.FlightDetailsException;
import exception.RecordNotFoundException;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FlightController {
	
	public static final String PAGE_NOT_FOUND_LOG_CATEGORY = "org.springframework.web.servlet.PageNotFound";

	@Autowired
	FlightService flightService;
	
	@Autowired
	FlightSearchService flightSearchService;
	

	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}
		return new ResponseEntity<>(body, headers, status);
	}
	
	@ExceptionHandler(RecordNotFoundException.class)
	@RequestMapping(value = "/flights", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Flight>> getAllFlight(){
		List<Flight> flights = flightService.getAllFlight();
		if (flights.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(flights, HttpStatus.OK);
		}
		
	}
	

	@ExceptionHandler(FlightByIdException.class)
	@RequestMapping(value = "/flights/{flightId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Flight> getFlight(@PathVariable Integer flightId){
		Flight flights = flightService.getFlightById(flightId);
		return new ResponseEntity<>(flights, HttpStatus.OK);
	}
	
	@ExceptionHandler(FlightCreationException.class)
	@RequestMapping(value = "/flights", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Flight> flightInsertion(@RequestBody Flight newFlight) {
		Flight updatedFlights = flightService.addFlight(newFlight);
		if (updatedFlights.getFlightNo() == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(newFlight, HttpStatus.OK);
		}			
	}
	
	@ExceptionHandler(FlightDeletionException.class)
	@RequestMapping(value = "/flights", method = RequestMethod.DELETE, consumes = "application/json")
	public ResponseEntity<Flight> flightDeletion(@RequestBody Flight flights) {
		if(flights.getFlightNo() == null) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		List <Flight> updatedFlights = flightService.deleteFlight(flights);
		if (updatedFlights.contains(flights.getFlightNo())){
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		} else {
			return new ResponseEntity<>(flights, HttpStatus.OK);
		}
	}
	
	@ExceptionHandler(FlightDetailsException.class)
	@RequestMapping(value = "/flights", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Flight> flightDetailsUpdate(@RequestBody Flight flight) {
		List <Flight> updatedFlights = flightService.updateFlight(flight);
		if(updatedFlights.size() == 0) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		} else {
			return new ResponseEntity<>(flight, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/OneWayNonLayover", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Flight>> OneWayNoLayover(
			@RequestBody OneWayBody body){
		try {
			List<Flight> flights = flightSearchService.FindOneWayNoLayover(
					body.getAirportDepId(), body.getAirportArrId(),
					body.getFlightDepBeginDate(), body.getFlightDepEndDate());	
			if (flights.size() == 0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(flights, HttpStatus.OK);
			}
		} catch (Exception e) {
			throw new RecordNotFoundException();
		}
	}
	
	@RequestMapping(value = "/OneWayLayover", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<ArrayList<Flight>>> OneWayLayover(
			@RequestBody OneWayBody body){
		try {
			List<ArrayList<Flight>> flights = flightSearchService.FindOneWayLayover(
					body.getAirportDepId(), body.getAirportArrId(),
					body.getFlightDepBeginDate(), body.getFlightDepEndDate());	
			if (flights.isEmpty() || flights == null) {
				return new ResponseEntity<>(null, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(flights, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(value = "/RoundTripNoLayover", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<List<Flight>>> RoundTripBodysNoLayovers(
			@RequestBody RoundTripBody body) {
		try {
			List<Flight> departFlights = flightSearchService.FindRoundTripDepartureNoLayover(
					body.getAirportDepId(), body.getAirportArrId(),
					body.getFlightDepBeginDate(), body.getFlightDepEndDate());	
			if (departFlights.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			List<Flight> returnFlights = flightSearchService.FindRoundTripReturnNoLayover(
					body.getAirportDepId(),  body.getAirportArrId(),
					body.getFlightRetBeginDate(), body.getFlightRetEndDate());
			
			if (returnFlights.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			List<List<Flight>> flights = new ArrayList<List<Flight>>(2);
			flights.add(departFlights);
			flights.add(returnFlights);
			return new ResponseEntity<>(flights, HttpStatus.OK);
		} catch (Exception e) {
			throw new RecordNotFoundException();
		}
	}
	
	@RequestMapping(value = "/RoundTripLayovers", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<List<ArrayList<Flight>>>> RoundTripLayovers(
			@RequestBody RoundTripBody body) {
		try {
			/* Get all layover and non-layover depart flights from A -> C */
			ArrayList<List<ArrayList<Flight>>> FlightCombinations = new ArrayList<List<ArrayList<Flight>>>(2);
			/* get depart layover flights (A -> B -> C) */
			List<ArrayList<Flight>> DepartFlights = flightSearchService.FindOneWayLayover(
					body.getAirportDepId(), body.getAirportArrId(),
					body.getFlightDepBeginDate(), body.getFlightDepEndDate());
			/* get depart nonlayover flights (A -> C) */
			ArrayList<Flight> DepartNoLayovers = (ArrayList<Flight>) flightSearchService.FindRoundTripDepartureNoLayover
					(body.getAirportDepId(), body.getAirportArrId(),
					body.getFlightDepBeginDate(), body.getFlightDepEndDate());
			/* combine depart nonlayovers and layovers */
			ListIterator<Flight> it = DepartNoLayovers.listIterator();
			while(it.hasNext()) {
				ArrayList<Flight> temp = new ArrayList<>();
				temp.add(it.next());
				if(DepartFlights != null) {
					DepartFlights.add(temp);
				} else {
					DepartFlights = new ArrayList<ArrayList<Flight>>();
					DepartFlights.add(temp);
				}
			}
			if(DepartFlights != null) {
				FlightCombinations.add(DepartFlights);
			}
			/* Get all layover and non-layover return flights from C -> A */
			List<ArrayList<Flight>> ReturnFlights = flightSearchService.FindOneWayLayover(
					 body.getAirportArrId(), body.getAirportDepId(),
					body.getFlightRetBeginDate(), body.getFlightRetEndDate());
			/* Get all non-layover return flights */
			ArrayList<Flight> ReturnNoLayovers = (ArrayList<Flight>) flightSearchService.FindRoundTripReturnNoLayover (
					body.getAirportDepId(), body.getAirportArrId(),   
					body.getFlightRetBeginDate(), body.getFlightRetEndDate());
			if(ReturnNoLayovers != null ) {
				ListIterator<Flight> it2 = ReturnNoLayovers.listIterator();
				while(it2.hasNext()) {
					ArrayList<Flight> temp = new ArrayList<>(1);
					temp.add(it2.next());
					System.out.println(temp.get(0).getFlightNo());
					if(ReturnFlights != null) {
						ReturnFlights.add(temp);
					} else {
						ReturnFlights = new ArrayList<ArrayList<Flight>>();
					}
				}
				if(ReturnFlights != null) {
					FlightCombinations.add(ReturnFlights);
				}
			}
			return new ResponseEntity<>(FlightCombinations, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
}