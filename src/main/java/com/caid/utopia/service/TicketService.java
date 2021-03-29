	package com.caid.utopia.service;

	import java.time.LocalDate;
import java.util.List;
	import java.util.Optional;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

import com.caid.utopia.entity.Tickets;
import com.caid.utopia.entity.Travelers;
import com.caid.utopia.entity.Tickets;
import com.caid.utopia.entity.Flights;
import com.caid.utopia.entity.Payments;
import com.caid.utopia.repo.TicketsRepo;
import com.caid.utopia.repo.TravelersRepo;
import com.caid.utopia.repo.TicketsRepo;
import com.caid.utopia.repo.FlightsRepo;
import com.caid.utopia.repo.PaymentsRepo;

import exception.RecordAlreadyExistsException;
import exception.RecordCreationException;
import exception.RecordForeignKeyConstraintException;
import exception.RecordNotFoundException;
import exception.RecordUpdateException;


	@Service
	public class TicketService {
		
		@Autowired
		FlightsRepo flightsRepo;
		
		@Autowired
		TicketsRepo ticketRepo;
		
		@Autowired
		TravelersRepo travelersRepo;
		
		@Autowired
		PaymentsRepo paymentsRepo;
				
		/* Create Tickets */
		public Tickets createTickets(Tickets ticket) throws RecordCreationException {
			try {
				/* Check field values */
				Flights flight = ticket.getFlight();
				Travelers traveler = ticket.getTraveler();
				Integer confirmationCode = ticket.getConfirmationCode();
				Float ticketPrice = ticket.getTicketPrice();
				LocalDate dateIssued = ticket.getDateIssued();
				Payments payment = ticket.getPayment();
				if(!travelersRepo.findById(traveler.getTravelerId()).isPresent()) {
					throw new RecordForeignKeyConstraintException();
				}
				if(!paymentsRepo.findById(payment.getPaymentId()).isPresent()) {
					throw new RecordForeignKeyConstraintException();
				}
				if(confirmationCode == null || confirmationCode < 0 
						|| ticketPrice == null || ticketPrice < 0
						|| dateIssued == null || dateIssued.compareTo(LocalDate.now()) > 0) {
					throw new RecordCreationException();
				}
				return ticketRepo.save(ticket);
			}catch(Exception e) {
				throw e;
			}
		}		
		/* Read all ticket */
		public List<Tickets> getAllTickets() throws RecordNotFoundException {
			
			try {
				List<Tickets> ticket = ticketRepo.findAll();
				return ticket;
			} catch (Exception e) {
				throw new RecordNotFoundException();
			}		
		}
		
		/* Read ticket by id */
		public Tickets getTicketsById(Integer id) throws RecordNotFoundException {
			try {
				Optional<Tickets> ticket = ticketRepo.findById(id);
				if(ticket.isPresent()) {
					return ticket.get();
				} else {
					throw new RecordNotFoundException();
				}
			}catch(Exception e) {
				throw new RecordNotFoundException();
			}
		}
		
		
		/* Update Tickets */
		public Tickets updateTickets(Tickets ticket) throws RecordUpdateException {
			try {
				if(ticketRepo.findById(ticket.getTicketNo()).isEmpty()) {
					throw new RecordUpdateException();
				}
				Tickets temp = ticketRepo.findById(ticket.getTicketNo()).get();
				float price = ticket.getTicketPrice();
				if(price >= 0) {
					temp.setTicketPrice(price); 
				}
				Integer confirmationCode = ticket.getConfirmationCode();
				if(confirmationCode != null && confirmationCode >= 0) {
					temp.setConfirmationCode(confirmationCode);
				}
				Flights flight = ticket.getFlight();
				if(flightsRepo.findById(flight.getFlightNo()).isPresent()) {
					temp.setFlight(flight);
				}
				Travelers traveler = ticket.getTraveler();
				if(travelersRepo.findById(traveler.getTravelerId()).isPresent()) {
					temp.setTraveler(traveler);
				}
				Payments payment = ticket.getPayment();
				if(paymentsRepo.findById(payment.getPaymentId()).isPresent()) {
					temp.setPayment(payment);
				}
				LocalDate dateIssued = ticket.getDateIssued();
				if(dateIssued.isBefore(LocalDate.now())) {
					temp.setDateIssued(dateIssued);
				}
				return ticketRepo.save(ticket);
			}catch(Exception e) {
				throw e;
			}
		}
		
		
		/* Delete Ticket */
		public void deleteTickets(Tickets ticket) throws RecordUpdateException {
			try {
				Optional<Tickets> temp = ticketRepo.findById(ticket.getTicketNo());
				if(temp.isEmpty()) {
					throw new RecordNotFoundException();
				}
				ticket = temp.get();
				ticketRepo.delete(ticket);
			}catch(Exception e) {
				throw e;
			}
		}
	}