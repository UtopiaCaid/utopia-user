package com.caid.utopia.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.caid.utopia.entity.Traveler;
import com.caid.utopia.entity.Account;
import com.caid.utopia.repo.TravelerRepo;
import com.caid.utopia.repo.AccountRepo;
import com.caid.utopia.repo.FlightRepo;
import com.caid.utopia.repo.PaymentRepo;
import com.caid.utopia.repo.TicketRepo;

import exception.RecordCreationException;
import exception.RecordForeignKeyConstraintException;
import exception.RecordHasDependenciesException;
import exception.RecordNotFoundException;
import exception.RecordUpdateException;


	@Service
	public class TravelerService {
		
		@Autowired
		FlightRepo flightRepo;
		
		@Autowired
		TravelerRepo travelerRepo;
		
		@Autowired
		PaymentRepo paymentsRepo;
		
		@Autowired
		AccountRepo accountRepo;
				
		@Autowired
		TicketRepo ticketRepo;
		
		/* Create Traveler */
		public Traveler createTraveler(Traveler traveler) throws RecordCreationException {
			try {
				/* Check field values */
				Traveler temp = new Traveler();
				Account account = accountRepo.findById(traveler.getAccount().getAccountNumber()).get();
				if(account == null) {
					throw new RecordForeignKeyConstraintException();
				}
				temp.setAccount(account);
				String firstName = traveler.getFirstName();
				String middleName = traveler.getMiddleName();
				if(firstName == null || firstName.length() <= 0 || firstName.length() > 45) {
					throw new RecordCreationException();
				}
				temp.setFirstName(firstName);
				if(middleName != null 
						&& middleName.length() > 0  && firstName.length() <= 45) {
					temp.setMiddleName(middleName);
				}
				String lastName = traveler.getLastName();
				if(lastName == null || lastName.length() <= 0 || lastName.length() > 45) {
					throw new RecordCreationException();
				}
				temp.setLastName(lastName);
				String gender = traveler.getGender();
				String ktn = traveler.getKnownTravelerNumber();
				if(ktn != null && ktn.length() > 0  && ktn.length() <= 45) {
					temp.setKnownTravelerNumber(ktn);
				}
				LocalDate dob = traveler.getDob();
				if(gender == null || gender.length() != 1
						|| dob == null || dob.compareTo(LocalDate.now()) > 0) {
					throw new RecordCreationException();
				}
				temp.setGender(gender);
				temp.setDob(dob);
				return travelerRepo.save(temp);
			}catch(Exception e) {
				throw e;
			}
		}		
		/* Read all travelers */
		public List<Traveler> getAllTraveler() throws RecordNotFoundException {
			
			try {
				List<Traveler> traveler = travelerRepo.findAll();
				return traveler;
			} catch (Exception e) {
				throw new RecordNotFoundException();
			}		
		}
		
		/* Read traveler by id */
		public Traveler getTravelerById(Integer id) throws RecordNotFoundException {
			try {
				Optional<Traveler> traveler = travelerRepo.findById(id);
				if(traveler.isPresent()) {
					return traveler.get();
				} else {
					throw new RecordNotFoundException();
				}
			}catch(Exception e) {
				throw new RecordNotFoundException();
			}
		}
		
		
		/* Update Traveler */
		public Traveler updateTraveler(Traveler traveler) throws RecordUpdateException {
			try {
				if(travelerRepo.findById(traveler.getTravelerId()).isEmpty()) {
					throw new RecordUpdateException();
				}
				Traveler temp = travelerRepo.findById(traveler.getTravelerId()).get();
				Account account = traveler.getAccount();
				if(travelerRepo.findById(traveler.getTravelerId()).isPresent()) {
					temp.setAccount(account);
				}
				String firstName = traveler.getFirstName();
				if(firstName != null && firstName.length() > 0 && firstName.length() <= 45) {
					temp.setFirstName(firstName);
				}
				String middleName = traveler.getMiddleName();
				if(middleName != null && middleName.length() > 0 && middleName.length() <= 45) {
					temp.setMiddleName(middleName);
				}
				String lastName = traveler.getLastName();
				if(lastName != null && lastName.length() > 0 && lastName.length() <= 45) {
					temp.setLastName(lastName);
				}
				String gender = traveler.getGender();
				if(gender != null && gender.length() == 1) {
					temp.setGender(gender);
				}
				String ktn = traveler.getKnownTravelerNumber();
				if(ktn != null && ktn.length() > 0 && ktn.length() <= 45) {
					temp.setKnownTravelerNumber(ktn);
				}
				LocalDate dob = traveler.getDob();
				if(dob != null && dob.isBefore(LocalDate.now())) {
					temp.setDob(dob);
				}
				return travelerRepo.save(temp);
			}catch(Exception e) {
				throw e;
			}
		}
		
		/* Delete Ticket */
		public void deleteTraveler(Traveler traveler) throws RecordUpdateException {
			try {
				Optional<Traveler> temp = travelerRepo.findById(traveler.getTravelerId());
				if(temp.isEmpty()) {
					throw new RecordNotFoundException();
				}
				traveler = temp.get();
				if(ticketRepo.TravelerHasTickets(traveler).isEmpty()) {
					travelerRepo.delete(traveler);
				} else {
					throw new RecordHasDependenciesException();
				}
			}catch(Exception e) {
				throw e;
			}
		}
	}