package com.caid.utopia.repo;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD:src/main/java/com/caid/utopia/repo/FlightsRepo.java
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.caid.utopia.entity.Aircraft;
import com.caid.utopia.entity.Flights;

@Repository
public interface FlightsRepo extends JpaRepository<Flights, Integer>{
	
	
=======
import org.springframework.stereotype.Repository;

import com.caid.utopia.entity.Airport;

@Repository
public interface AirportRepo extends JpaRepository<Airport, Integer>{

>>>>>>> development:src/main/java/com/caid/utopia/repo/AirportRepo.java
}
