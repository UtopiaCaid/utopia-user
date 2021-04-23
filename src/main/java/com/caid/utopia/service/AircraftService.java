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
	}