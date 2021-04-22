package com.caid.utopia.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.caid.utopia.entity.Aircraft;
import com.caid.utopia.entity.AircraftType;
import com.caid.utopia.entity.Flight;
import com.caid.utopia.entity.Ticket;

@Repository
public interface AircraftRepo extends JpaRepository<Aircraft, Integer>{
	
		@Query("FROM Aircraft WHERE aircraftType = :curr ")
		List<Aircraft> AircraftTypeHasAircraft(@Param("curr") AircraftType curr);
}
		
		

