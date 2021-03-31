package com.caid.utopia.repo;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.caid.utopia.entity.Flight;

@Repository
public interface FlightRepo extends JpaRepository<Flight, Integer>{

}
