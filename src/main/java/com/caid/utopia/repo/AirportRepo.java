package com.caid.utopia.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caid.utopia.entity.Airport;

@Repository
public interface AirportRepo extends JpaRepository<Airport, Integer>{

}
