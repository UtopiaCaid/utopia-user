package com.caid.utopia.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.caid.utopia.entity.Account;
import com.caid.utopia.entity.AccountRole;
import com.caid.utopia.entity.Flight;
import com.caid.utopia.entity.Ticket;
import com.caid.utopia.entity.Traveler;
import com.caid.utopia.entity.Account;
import com.caid.utopia.repo.AccountRepo;
import com.caid.utopia.repo.AccountRoleRepo;
import com.caid.utopia.repo.AccountRepo;
import com.caid.utopia.repo.FlightRepo;
import com.caid.utopia.repo.PaymentRepo;
import com.caid.utopia.repo.TicketRepo;
import com.caid.utopia.repo.TravelerRepo;

import exception.RecordCreationException;
import exception.RecordForeignKeyConstraintException;
import exception.RecordHasDependenciesException;
import exception.RecordNotFoundException;
import exception.RecordUpdateException;


	@Service
	public class UserService {
		
		@Autowired
		FlightRepo flightRepo;
		
		@Autowired
		PaymentRepo paymentRepo;
		
		@Autowired
		TravelerRepo travelerRepo;
		
		@Autowired
		TicketRepo ticketRepo;
		
		@Autowired
		AccountRepo accountRepo;
		
		@Autowired
		AccountRoleRepo accountRoleRepo;
				
		/* Create Account */
		public Account createAccount(Account account) throws RecordCreationException {
			try {
				/* Check field values */
				Account temp = new Account();
				AccountRole role = accountRoleRepo.findById(account.getRole().getRoleId()).get();
				if(role == null) {
					throw new RecordForeignKeyConstraintException();
				}
				temp.setRole(role);
				String email = account.getEmail();
				String username = account.getUsername();
				String password = account.getPassword();
				if(email == null || email.length() < 3 || email.length() > 45) {
					throw new RecordCreationException();
				}
				if(username == null || username.length() <= 0 || username.length() > 45) {
					throw new RecordCreationException();
				}
				if(password == null || password.length() <= 0 || password.length() > 100) {
					throw new RecordCreationException();
				}
				temp.setEmail(email);
				temp.setUsername(username);
				temp.setPassword(password);
				temp.setDateCreated(LocalDate.now());
				return accountRepo.save(temp);
			}catch(Exception e) {
				throw e;
			}
		}		
		
		/* Find Account Flight History (includes upcoming flights) */
		public Set<Flight> getAccountFlightHistory(Account account) throws RecordNotFoundException {
			try {
				Set<Flight> flights = flightRepo.FindAccountFlightHistory(account);
				return flights;
			} catch (Exception e) {
				throw e;
			}
		}
		
		/* Find Account Upcoming Flights */
		public Set<Flight> getAccountUpcomingFlights(Account account) throws RecordNotFoundException {
			try {
				Set<Flight> flights = flightRepo.FindAccountUpcomingFlights(account);
				return flights;
			} catch (Exception e) {
				throw e;
			}
		}
		
		/* Find Account Ticket History */
		public Set<Ticket> getAccountTicketHistory(Account account) throws RecordNotFoundException {
			try {
				Set<Ticket> tickets = ticketRepo.FindAccountTicketHistory(account);
				return tickets;
			} catch (Exception e) {
				throw e;
			}
		}
		/* Find Account Upcoming Tickets */
		public Set<Ticket> getAccountUpcomingTickets(Account account) throws RecordNotFoundException {
			try {
				Set<Ticket> tickets = ticketRepo.FindAccountUpcomingTickets(account);
				return tickets;
			} catch (Exception e) {
				throw e;
			}
		}
		
		/* Return arraylist of available class seats for a flight */
		public ArrayList<Integer> getFlightAvailableSeats(Flight flight) throws RecordNotFoundException {
			try {
				if(flightRepo.findById(flight.getFlightNo()).isEmpty()) {
					throw new RecordNotFoundException();
				}
				flight = flightRepo.findById(flight.getFlightNo()).get();
				ArrayList<Integer> seats = new ArrayList<Integer>(3);
				seats.add(0, (flight.getAircraft().getFirstClassCount() == null 
						? 0 
						: flight.getAircraft().getFirstClassCount()) - ticketRepo.FindFirstClassTicketsCount(flight));
				seats.add(1, (flight.getAircraft().getSecondClassCount() == null 
						? 0 
						: flight.getAircraft().getSecondClassCount()) - ticketRepo.FindSecondClassTicketsCount(flight));
				seats.add(2, (flight.getAircraft().getThirdClassCount() == null 
						? 0 
						: flight.getAircraft().getThirdClassCount()) - ticketRepo.FindThirdClassTicketsCount(flight));
				return seats;
			} catch (Exception e) {
				throw e;
			}
		}
		
		/* cancel all of an account's traveler tickets for a specific flight */
		@Transactional
		public void deleteAllAccountFlightTickets(Account account, Flight flight) throws RecordNotFoundException{
			account = accountRepo.findById(account.getAccountNumber()).get();
			flight = flightRepo.findById(flight.getFlightNo()).get();
			List<Ticket> tickets = ticketRepo.DeleteAllAccountFlightTickets(account, flight);
			if(!tickets.isEmpty()) {
				ticketRepo.deleteInBatch(tickets);
			}
			/* REFUND PAYMENT WIP */
		}
		/* cancel all flight tickets for a traveler */
		
		@Transactional
		public void DeleteAllTravelerTickets(Traveler traveler) throws RecordNotFoundException{
			traveler = travelerRepo.findById(traveler.getTravelerId()).get();
			List<Ticket> tickets = ticketRepo.DeleteAllTravelerTickets(traveler);
			if(!tickets.isEmpty()) {
				ticketRepo.deleteInBatch(tickets);
			}
			/* REFUND PAYMENT WIP */
		}
		/* cancel a flight ticket for a traveler */
		@Transactional
		public void DeleteFlightTicket(Traveler traveler, Flight flight) throws RecordNotFoundException{
			traveler = travelerRepo.findById(traveler.getTravelerId()).get();
			flight = flightRepo.findById(flight.getFlightNo()).get();
			List<Ticket> tickets = ticketRepo.DeleteTravelerFlightTicket(traveler, flight);
			if(tickets.size() != 1) {
				throw new RecordNotFoundException();
			} else {
				ticketRepo.delete(tickets.get(0));
			}
			/* REFUND PAYMENT WIP */
		}
	}