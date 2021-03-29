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

import exception.RecordCreationException;
import exception.RecordForeignKeyConstraintException;
import exception.RecordNotFoundException;
import exception.RecordUpdateException;


	@Service
	public class TravelerService {
		
		@Autowired
		FlightRepo flightsRepo;
		
		@Autowired
		TravelerRepo travelerRepo;
		
		@Autowired
		PaymentRepo paymentsRepo;
		
		@Autowired
		AccountRepo accountRepo;
				
		/* Create Traveler */
		public Traveler createTraveler(Traveler traveler) throws RecordCreationException {
			try {
				/* Check field values */
				Account account = traveler.getAccount();
				if(!(accountRepo.findById(account.getAccountNumber())).isPresent()) {
					throw new RecordForeignKeyConstraintException();
				}
				String firstName = traveler.getFirstName();
				String middleName = traveler.getMiddleName();
				if(middleName.length() <= 0 || middleName.length() > 45) {
					throw new RecordCreationException();
				}
				String lastName = traveler.getLastName();
				String gender = traveler.getGender();
				String ktn = traveler.getKnownTravelerNumber();
				if(ktn.length() <= 0 || ktn.length() > 45) {
					throw new RecordCreationException();
				}
				LocalDate dob = traveler.getDob();
				if(firstName == null || firstName.length() <= 0 || firstName.length() > 45 
						|| lastName == null || lastName.length() <= 0 || lastName.length() > 45 
						|| gender == null || gender.length() != 1
						|| dob == null || dob.compareTo(LocalDate.now()) > 0) {
					throw new RecordCreationException();
				}
				return travelerRepo.save(traveler);
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
				if(firstName.length() > 0 && firstName.length() <= 45) {
					temp.setFirstName(firstName);
				}
				String middleName = traveler.getMiddleName();
				if(middleName.length() > 0 && middleName.length() <= 45) {
					temp.setFirstName(middleName);
				}
				String lastName = traveler.getLastName();
				if(lastName.length() > 0 && lastName.length() <= 45) {
					temp.setFirstName(lastName);
				}
				String gender = traveler.getGender();
				if(gender.length() == 1) {
					temp.setGender(gender);
				}
				String ktn = traveler.getKnownTravelerNumber();
				if(ktn.length() > 0 && ktn.length() <= 45) {
					temp.setFirstName(ktn);
				}
				LocalDate dob = traveler.getDob();
				if(dob.isBefore(LocalDate.now())) {
					temp.setDob(dob);
				}
				return travelerRepo.save(traveler);
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
				travelerRepo.delete(traveler);
			}catch(Exception e) {
				throw e;
			}
		}
	}