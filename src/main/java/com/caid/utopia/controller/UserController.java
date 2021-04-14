package com.caid.utopia.controller;

import java.util.List;
import java.util.Set;

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
import com.caid.utopia.entity.Ticket;
import com.caid.utopia.entity.Traveler;
import com.caid.utopia.entity.userRequestBody.AccountFlight;
import com.caid.utopia.entity.userRequestBody.TravelerFlight;
import com.caid.utopia.entity.Account;
import com.caid.utopia.service.AccountService;
import com.caid.utopia.service.TicketService;
import com.caid.utopia.service.UserService;

import exception.RecordNotFoundException;
import exception.RecordCreationException;
import exception.RecordForeignKeyConstraintException;
import exception.ExceptionReducer;
import exception.RecordAlreadyExistsException;
import exception.RecordUpdateException;
import exception.RecordHasDependenciesException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {
	

	@Autowired
	AccountService accountService;
	
	@Autowired
	UserService userService;
	
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
	
	/* get account flight history */
	@RequestMapping(value = "/Account/Flight/History", method = RequestMethod.GET, produces = "application/json", consumes = "application/json") 
	public ResponseEntity<Object> getAccountFlightHistory(@RequestBody Account account) throws Exception {
		try {
			account = accountService.getAccountById(account.getAccountNumber());
			Set<Flight> flights = userService.getAccountFlightHistory(account);
			if(flights.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(flights, HttpStatus.OK);
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}	
	
	/* get account upcoming flights */
	@RequestMapping(value = "/Account/Flight", method = RequestMethod.GET, produces = "application/json", consumes = "application/json") 
	public ResponseEntity<Object> getAccountUpcomingFlights(@RequestBody Account account) throws Exception {
		try {
			account = accountService.getAccountById(account.getAccountNumber());
			Set<Flight> flights = userService.getAccountUpcomingFlights(account);
			if(flights.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(flights, HttpStatus.OK);
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	/* get account ticket history */
	@RequestMapping(value = "/Account/Ticket/History", method = RequestMethod.GET, produces = "application/json", consumes = "application/json") 
	public ResponseEntity<Object> getAccountTickets(@RequestBody Account account) throws Exception {
		try {
			account = accountService.getAccountById(account.getAccountNumber());
			Set<Ticket> tickets = userService.getAccountTicketHistory(account);
			if(tickets.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(tickets, HttpStatus.OK);
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	/* get account upcoming tickets */
	@RequestMapping(value = "/Account/Ticket", method = RequestMethod.GET, produces = "application/json", consumes = "application/json") 
	public ResponseEntity<Object> getAccountUpcomingTickets(@RequestBody Account account) throws Exception {
		try {
			account = accountService.getAccountById(account.getAccountNumber());
			Set<Ticket> tickets = userService.getAccountUpcomingTickets(account);
			if(tickets.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(tickets, HttpStatus.OK);
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	/* get account travelers */
	@RequestMapping(value = "/Account/Traveler", method = RequestMethod.GET, produces = "application/json", consumes = "application/json") 
	public ResponseEntity<Object> getAccountTravelers(@RequestBody Account account) throws Exception {
		try {
			account = accountService.getAccountById(account.getAccountNumber());
			List<Traveler> traveler = account.getTravelers();
			if(traveler.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(traveler, HttpStatus.OK);
			}
		} catch (Exception e) {
			return handleException(e);
		}

	}
	
	/* get flight available seats */
	@RequestMapping(value = "/Flight/Seats", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> getFlightAvailableSeats(@RequestBody Flight flight) throws Exception {
		try {
			return new ResponseEntity<>(userService.getFlightAvailableSeats(flight), HttpStatus.OK);
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	/* cancel all flight tickets for a account-flight pair */
	@RequestMapping(value = "/Flight/Account/Tickets", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> deleteAllAccountFlightTickets(@RequestBody AccountFlight accountFlight) throws Exception {
		try {
			userService.deleteAllAccountFlightTickets(accountFlight.getAccount(), accountFlight.getFlight());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	/* cancel all flight tickets for a traveler */
	@RequestMapping(value = "/Flight/Traveler/Tickets", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> deleteAllTravelerTickets(@RequestBody Traveler traveler) throws Exception {
		try {
			userService.DeleteAllTravelerTickets(traveler);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	/* delete a flight ticket for a traveler */
	@RequestMapping(value = "/Flight/Traveler/Ticket", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> deleteTravelerFlightTicket(@RequestBody TravelerFlight travelerFlight) throws Exception {
		try {
			userService.DeleteFlightTicket(travelerFlight.getTraveler(), travelerFlight.getFlight());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return handleException(e);
		}
	}
}