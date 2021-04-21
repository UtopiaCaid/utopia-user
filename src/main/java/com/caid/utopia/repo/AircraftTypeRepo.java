package com.caid.utopia.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caid.utopia.entity.AircraftType;

@Repository
public interface AircraftTypeRepo extends JpaRepository<AircraftType, Integer>{

}
