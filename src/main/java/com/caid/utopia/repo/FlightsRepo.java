package com.caid.utopia.repo;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.caid.utopia.entity.Flights;

@Repository
public interface FlightsRepo extends JpaRepository<Flights, Integer>{

}
