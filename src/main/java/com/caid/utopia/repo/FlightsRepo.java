package com.caid.utopia.repo;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.caid.utopia.entity.Aircraft;
import com.caid.utopia.entity.Flights;

@Repository
public interface FlightsRepo extends JpaRepository<Flights, Integer>{
	
	
}
