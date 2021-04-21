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
import com.caid.utopia.entity.Ticket;
import com.caid.utopia.service.TicketService;
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
public class TicketController {
	

	@Autowired
	TicketService ticketService;
	
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
	@RequestMapping(value = "/Ticket", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Ticket>> getAllTicket(){
		List<Ticket> ticket = ticketService.getAllTickets();
		System.out.println(ticket.size());
		if( ticket.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(ticket, HttpStatus.OK);
		}
	}
	
	/* get record by id */
	@RequestMapping(value = "/Ticket/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Ticket> getTicketById(@PathVariable Integer id){
		Ticket ticket = ticketService.getTicketsById(id);
		if(ticket.getTicketNo() != id) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(ticket, HttpStatus.OK);
		}	
	}
	
	
	/* create record */
	@Transactional
	@RequestMapping(value = "/Ticket", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> createTicket(@RequestBody Ticket ticket) throws Exception {
		try {
			if(ticketService.createTicket(ticket) instanceof Ticket) {
				return new ResponseEntity<>(ticket, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	/* update record */
	@Transactional
	@RequestMapping(value = "/Ticket", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> updateTicket(@RequestBody Ticket ticket) throws Exception {
		try {
			if(ticketService.updateTicket(ticket) instanceof Ticket) {
				return new ResponseEntity<>(ticket, HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	/* update just flight */
	@Transactional
	@RequestMapping(value = "/Ticket/Flight", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> changeFlight(@RequestBody Ticket ticket) throws Exception {
		try {
			if(ticketService.changeFlight(ticket) instanceof Ticket) {
				return new ResponseEntity<>(ticket, HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	/* delete record */
	@Transactional
	@RequestMapping(value = "/Ticket", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> deleteTicket(@RequestBody Ticket ticket) throws Exception {
		try {
			ticketService.deleteTicket(ticket);
			return new ResponseEntity<>(ticket, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
}