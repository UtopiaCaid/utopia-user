	package com.caid.utopia.service;

	import java.util.List;
	import java.util.Optional;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

import com.caid.utopia.entity.Account;
import com.caid.utopia.entity.Aircraft;
import com.caid.utopia.entity.AircraftType;
import com.caid.utopia.entity.Flight;
import com.caid.utopia.repo.AircraftRepo;
import com.caid.utopia.repo.AircraftTypeRepo;
import com.caid.utopia.repo.FlightRepo;

import exception.RecordAlreadyExistsException;
import exception.RecordCreationException;
import exception.RecordForeignKeyConstraintException;
import exception.RecordHasDependenciesException;
import exception.RecordNotFoundException;
import exception.RecordUpdateException;


	@Service
	public class AircraftService {
		
		@Autowired
		FlightRepo FlightsRepo;
		
		@Autowired
		FlightRepo flightRepo;
		
		@Autowired
		AircraftRepo aircraftRepo;
		
		@Autowired
		AircraftTypeRepo aircraftTypeRepo;
		
		/* Create aircraft type */
		//public Aircraft createAircraftType() throws 
		
		/* Read all aircraft */
		public List<Aircraft> getAllAircraft() throws RecordNotFoundException {
			
			try {
				List<Aircraft> aircraft = aircraftRepo.findAll();
				return aircraft;
			} catch (Exception e) {
				throw new RecordNotFoundException();
			}		
		}
		
		/* Read aircraft by id */
		public Aircraft getAircraftById(Integer id) throws RecordNotFoundException {
			try {
				Optional<Aircraft> aircraft = aircraftRepo.findById(id);
				if(aircraft.isPresent()) {
					return aircraft.get();
				} else {
					throw new RecordNotFoundException();
				}
			}catch(Exception e) {
				throw new RecordNotFoundException();
			}
		}
		
		/* Read all aircraft types */
		public List<AircraftType> getAllAircraftTypes() throws RecordNotFoundException {
			
			try {
				List<AircraftType> aircraft = aircraftTypeRepo.findAll();
				return aircraft;
			} catch (Exception e) {
				throw new RecordNotFoundException();
			}		
		}
		/* Read aircraft type by id */
		public AircraftType getAircraftTypeById(Integer id) throws RecordNotFoundException {
			try {
				Optional<AircraftType> aircraftType = aircraftTypeRepo.findById(id);
				if(aircraftType.isPresent()) {
					return aircraftType.get();
				} else {
					throw new RecordNotFoundException();
				}
			}catch(Exception e) {
				throw new RecordNotFoundException();
			}
		}
		/* Create Aircraft */
		public Aircraft createAircraft(Aircraft aircraft) throws RecordCreationException {
			try {
				/* Check field values */
				AircraftType aircraftType = aircraft.getAircraftType();
				Integer seat_count = aircraft.getSeatCount();
				Integer first_class = aircraft.getFirstClassCount();
				Integer second_class = aircraft.getSecondClassCount();
				Integer third_class = aircraft.getThirdClassCount();
				if(!aircraftTypeRepo.findById(aircraftType.getAircraftTypeId()).isPresent()) {
					throw new RecordForeignKeyConstraintException();
				}
				if(first_class < 0 || second_class < 0 || third_class < 0 
						|| first_class + second_class + third_class > seat_count) {
					throw new RecordCreationException();
				}
				return aircraftRepo.save(aircraft);
			}catch(Exception e) {
				throw e;
			}
		}
		
		/* Create AircraftType */
		public AircraftType createAircraftType(AircraftType aircraftType) throws RecordCreationException {
			try {
				if(aircraftTypeRepo.findById(aircraftType.getAircraftTypeId()).isPresent()) {
					throw new RecordAlreadyExistsException();
				}
				
				/* Check all field types before api call */
				String name = aircraftType.getaircraftTypeName();
				Integer seats = aircraftType.getSeatMaximum();
				String manufacturer = aircraftType.getManufacturer();
				if(name == null || name.length() < 1 || name.length() > 45) {
					throw new RecordCreationException();
				}
				if(seats == null || seats < 0) {
					throw new RecordCreationException();
				}
				if(manufacturer == null || manufacturer.length() < 1 || manufacturer.length() > 45) {
					throw new RecordCreationException();
				}
				return aircraftTypeRepo.save(aircraftType);
			}catch(Exception e) {
				throw e;
			}
		}
		
		/* Update Aircraft */
		public Aircraft updateAircraft(Aircraft aircraft) throws RecordUpdateException {
			try {
				Optional<AircraftType> aircraftType = aircraftTypeRepo.findById(aircraft.getAircraftType().getAircraftTypeId());
				if(aircraftRepo.findById(aircraft.getAircraftId()).isEmpty()) {
					throw new RecordUpdateException();
				}
				if(aircraftType.isEmpty()) {
					throw new RecordForeignKeyConstraintException();
				}
				if(!aircraftType.get().getAircraftTypeId().equals(aircraft.getAircraftType().getAircraftTypeId())) {
					throw new RecordUpdateException();
				}
				Aircraft updatedAircraft = aircraftRepo.findById(aircraft.getAircraftId()).get();
				Integer seat_count = aircraft.getSeatCount();
				if(seat_count > 0) {
					updatedAircraft.setSeatCount(seat_count);
				}
				Integer first_class = aircraft.getFirstClassCount();
				Integer second_class = aircraft.getSecondClassCount();
				Integer third_class = aircraft.getThirdClassCount();
				if(first_class > 0) {
					updatedAircraft.setFirstClassCount(first_class);
				}
				if(second_class > 0) {
					updatedAircraft.setSecondClassCount(second_class);
				}
				if(third_class > 0) {
					updatedAircraft.setThirdClassCount(third_class);
				}
				String status = aircraft.getAircraftStatus();
				if(status != null) {
					updatedAircraft.setAircraftStatus(status);
				}
				if(updatedAircraft.getSeatCount() < 
						updatedAircraft.getFirstClassCount() + updatedAircraft.getSecondClassCount()
						+ updatedAircraft.getThirdClassCount()) {
					throw new RecordCreationException();
				}
				if((first_class != null && first_class < 0) || (second_class != null && second_class < 0) 
						|| (third_class != null && third_class < 0) 
						|| (status != null && status.length() <= 0 && status.length() > 45)
						|| (first_class + second_class + third_class > seat_count)) 
				{
					throw new RecordCreationException();
				} else {
					return aircraftRepo.save(updatedAircraft);
				}
			}catch(Exception e) {
				throw e;
			}
		}
		
		/* Update Aircraft Type*/
		public AircraftType updateAircraftType(AircraftType aircraftType) throws RecordUpdateException {
			try {
				Optional<AircraftType> temp = aircraftTypeRepo.findById(aircraftType.getAircraftTypeId());
				if(temp.isEmpty()) {
					throw new RecordUpdateException();
				}
				AircraftType updatedAircraftType = temp.get();
				String name = aircraftType.getaircraftTypeName();
				if(name.length() > 0 && name.length() <= 45){
					updatedAircraftType.setaircraftTypeName(name);
				}
				Integer seat_maximum = aircraftType.getSeatMaximum();
				if(seat_maximum > 0) {
					updatedAircraftType.setSeatMaximum(seat_maximum);
				}
				String manufacturer = aircraftType.getManufacturer();
				if(manufacturer.length() > 0 && manufacturer.length() <= 45){
					updatedAircraftType.setManufacturer(manufacturer);
				}
				if(name == null || seat_maximum == null || manufacturer == null
						|| seat_maximum < 0 || name.length() <= 0 || name.length() > 45
						|| manufacturer.length() <= 0 || manufacturer.length() > 45) 
				{
					throw new RecordCreationException();	
				}
				return aircraftTypeRepo.save(aircraftType);
			}catch(Exception e) {
				throw e;
			}
		}
		
		/* Delete Aircraft */
		public void deleteAircraft(Aircraft aircraft) throws RecordNotFoundException {
			try {
				Optional<Aircraft> temp = aircraftRepo.findById(aircraft.getAircraftId());
				if(temp.isEmpty()) {
					throw new RecordNotFoundException();
				}
				aircraft = temp.get();
				if(flightRepo.AircraftHasFlights(aircraft).size() > 0) {
					throw new RecordHasDependenciesException();
				} else {
					aircraftRepo.delete(aircraft);
				}
			}catch(Exception e) {
				throw e;
			}
		}
		/* Delete Aircraft Type */
		public void deleteAircraftType(AircraftType aircraftType) throws RecordNotFoundException {
			try {
				Optional<AircraftType> temp = aircraftTypeRepo.findById(aircraftType.getAircraftTypeId());
				if(temp.isEmpty()) {
					throw new RecordNotFoundException();
				}
				aircraftType = temp.get();
				if(aircraftRepo.AircraftTypeHasAircraft(aircraftType).size() > 0) {
					throw new RecordHasDependenciesException();
				} else {
					aircraftTypeRepo.delete(aircraftType);
				}
			}catch(Exception e) {
				throw e;
			}
		}
		/* Deactivate Aircraft */
		public Aircraft deactivateAircraft(Aircraft aircraft) throws RecordUpdateException {
			try {
				Optional<Aircraft> temp = aircraftRepo.findById(aircraft.getAircraftId());
				if(temp.isEmpty()) {
					throw new RecordUpdateException();
				}
				aircraft = temp.get();
				aircraft.setAircraftStatus("Inactive");
				return aircraftRepo.save(aircraft);
			}catch(Exception e) {
				throw e;
			}
		}
		
		/* Activate Aircraft */
		public Aircraft activateAircraft(Aircraft aircraft) throws RecordUpdateException {
			try {
				Optional<Aircraft> temp = aircraftRepo.findById(aircraft.getAircraftId());
				if(temp == null) {
					throw new RecordUpdateException();
				}
				aircraft = temp.get();
				aircraft.setAircraftStatus("Active");
				return aircraftRepo.save(aircraft);
			}catch(Exception e) {
				throw e;
			}
		}
	}