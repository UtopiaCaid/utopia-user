	package com.caid.utopia.service;

	import java.util.List;
	import java.util.Optional;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

import com.caid.utopia.entity.Aircraft;
import com.caid.utopia.entity.AircraftType;
import com.caid.utopia.entity.Flights;
import com.caid.utopia.repo.AircraftRepo;
import com.caid.utopia.repo.AircraftTypeRepo;
import com.caid.utopia.repo.FlightsRepo;

	import exception.RecordNotFoundException;


	@Service
	public class AircraftService {
		
		@Autowired
		FlightsRepo FlightsRepo;
		
		@Autowired
		FlightsRepo flightsRepo;
		
		@Autowired
		AircraftRepo aircraftRepo;
		
		@Autowired
		AircraftTypeRepo aircraftTypeRepo;
		
		/* Read all aircraft */
		public List<Aircraft> getAllAircraft() throws RecordNotFoundException {
			
			try {
				List<Aircraft> aircraft = aircraftRepo.findAll();
				return aircraft;
			} catch (Exception e) {
				throw new RecordNotFoundException();
			}		
		}
		
		/* read aircraft by id */
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
		/* read aircraft type by id */
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