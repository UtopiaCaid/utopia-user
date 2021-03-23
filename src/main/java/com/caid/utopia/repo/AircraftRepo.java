package com.caid.utopia.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caid.utopia.entity.Aircraft;

@Repository
public interface AircraftRepo extends JpaRepository<Aircraft, Integer>{

}
