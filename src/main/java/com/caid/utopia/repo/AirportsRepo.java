package com.caid.utopia.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caid.utopia.entity.Airports;

@Repository
public interface AirportsRepo extends JpaRepository<Airports, Integer>{

}
