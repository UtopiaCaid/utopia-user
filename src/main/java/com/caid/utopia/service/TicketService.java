package com.caid.utopia.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.caid.utopia.entity.Ticket;
import com.caid.utopia.entity.Traveler;
import com.caid.utopia.entity.Flight;
import com.caid.utopia.entity.Payment;
import com.caid.utopia.repo.TicketRepo;
import com.caid.utopia.repo.TravelerRepo;
import com.caid.utopia.repo.FlightRepo;
import com.caid.utopia.repo.PaymentRepo;

import exception.RecordCreationException;
import exception.RecordForeignKeyConstraintException;
import exception.RecordNotFoundException;
import exception.RecordUpdateException;


	@Service
	public class TicketService {
		
		@Autowired
		FlightRepo flightsRepo;
		
		@Autowired
		TicketRepo ticketRepo;
		
		@Autowired
		TravelerRepo travelerRepo;
		
		@Autowired
		PaymentRepo paymentRepo;
				
		/* Create Tickets */
		public Ticket createTicket(Ticket ticket) throws RecordCreationException {
			try {
				/* Check field values */
				Flight flight = flightsRepo.findById(ticket.getFlight().getFlightNo()).get();
				Traveler traveler = travelerRepo.findById(ticket.getTraveler().getTravelerId()).get();
				Payment payment = paymentRepo.findById(ticket.getPayment().getPaymentId()).get();
				if(flight == null || traveler == null || payment == null) {
					throw new RecordForeignKeyConstraintException();
				}
				Integer confirmationCode = ticket.getConfirmationCode();
				Float ticketPrice = ticket.getTicketPrice();
				ticket.setDateIssued(LocalDate.now());
				if(confirmationCode == null || confirmationCode < 0 
						|| ticketPrice == null || ticketPrice < 0) {
					throw new RecordCreationException();
				}
				return ticketRepo.save(ticket);
			}catch(Exception e) {
				throw e;
			}
		}		
		/* Read all ticket */
		public List<Ticket> getAllTickets() throws RecordNotFoundException {
			try {
				List<Ticket> tickets = ticketRepo.findAll();
				return tickets;
			} catch (Exception e) {
				throw new RecordNotFoundException();
			}		
		}
		
		/* Read ticket by id */
		public Ticket getTicketsById(Integer id) throws RecordNotFoundException {
			try {
				Optional<Ticket> ticket = ticketRepo.findById(id);
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
		public Ticket updateTicket(Ticket ticket) throws RecordUpdateException {
			try {
				if(ticketRepo.findById(ticket.getTicketNo()).isEmpty()) {
					throw new RecordUpdateException();
				}
				Ticket temp = ticketRepo.findById(ticket.getTicketNo()).get();
				float price = ticket.getTicketPrice();
				if(price >= 0) {
					temp.setTicketPrice(price); 
				}
				Integer confirmationCode = ticket.getConfirmationCode();
				if(confirmationCode != null && confirmationCode >= 0) {
					temp.setConfirmationCode(confirmationCode);
				}
				Flight flight = ticket.getFlight();
				if(flightsRepo.findById(flight.getFlightNo()).isPresent()) {
					temp.setFlight(flightsRepo.findById(flight.getFlightNo()).get());
				}
				Traveler traveler = ticket.getTraveler();
				if(travelerRepo.findById(traveler.getTravelerId()).isPresent()) {
					temp.setTraveler(travelerRepo.findById(traveler.getTravelerId()).get());
				}
				Payment payment = ticket.getPayment();
				if(paymentRepo.findById(payment.getPaymentId()).isPresent()) {
					temp.setPayment(paymentRepo.findById(payment.getPaymentId()).get());
				}
				LocalDate dateIssued = ticket.getDateIssued();
				if(dateIssued != null && dateIssued.isBefore(LocalDate.now())) {
					temp.setDateIssued(dateIssued);
				}
				return ticketRepo.save(temp);
			}catch(Exception e) {
				throw e;
			}
		}
		
		/* Change flight if necessary */
		public Ticket changeFlight(Ticket ticket) throws RecordUpdateException{
			Ticket newTicket = ticketRepo.findById(ticket.getTicketNo()).get();
			Flight temp = flightsRepo.findById(ticket.getFlight().getFlightNo()).get();
			if(temp == null || temp.getFlightNo() == null) {
				throw new RecordUpdateException();
			} else {
				newTicket.setFlight(temp);
				return ticketRepo.save(newTicket);
			}
		}
		
		/* Delete Ticket */
		public void deleteTicket(Ticket ticket) throws RecordUpdateException {
			try {
				Optional<Ticket> temp = ticketRepo.findById(ticket.getTicketNo());
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