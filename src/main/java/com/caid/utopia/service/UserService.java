package com.caid.utopia.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.caid.utopia.entity.Account;
import com.caid.utopia.entity.AccountRole;
import com.caid.utopia.entity.Flight;
import com.caid.utopia.entity.Ticket;
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
		/* Read all accounts */
		public List<Account> getAllAccounts() throws RecordNotFoundException {
			
			try {
				List<Account> account = accountRepo.findAll();
				return account;
			} catch (Exception e) {
				throw new RecordNotFoundException();
			}		
		}
		
		/* Read account by id */
		public Account getAccountById(Integer id) throws RecordNotFoundException {
			try {
				Optional<Account> account = accountRepo.findById(id);
				if(account.isPresent()) {
					return account.get();
				} else {
					throw new RecordNotFoundException();
				}
			}catch(Exception e) {
				throw new RecordNotFoundException();
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
		
		/* Update Account */
		public Account updateAccount(Account account) throws RecordUpdateException {
			try {
				if(accountRepo.findById(account.getAccountNumber()).isEmpty()) {
					throw new RecordUpdateException();
				}
				Account temp = accountRepo.findById(account.getAccountNumber()).get();
				String email = account.getEmail();
				String username = account.getUsername();
				String password = account.getPassword();
				if(email != null && email.length() >= 3 && email.length() <= 45) {
					temp.setEmail(email);
				}
				if(username != null && username.length() > 0 && username.length() <= 45) {
					temp.setUsername(username);
				}
				if(password != null && password.length() > 0 && password.length() <= 100) {
					temp.setPassword(password);
				}
				return accountRepo.save(temp);
			}catch(Exception e) {
				throw e;
			}
		}
		
		/* Deactivate Account */
		public Account deactivateAccount(Account account) throws RecordUpdateException {
			try {
				Optional<Account> temp = accountRepo.findById(account.getAccountNumber());
				if(temp.isEmpty()) {
					throw new RecordNotFoundException();
				}
				account = temp.get();
				account.setRole(accountRoleRepo.findById(3).get());
				account.setEmail(null);
				account.setPassword(null);
				account.setUsername(null);
				return accountRepo.save(account);
			}catch(Exception e) {
				throw e;
			}
		}
	}