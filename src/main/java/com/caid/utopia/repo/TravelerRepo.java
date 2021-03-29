package com.caid.utopia.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caid.utopia.entity.Traveler;

@Repository
public interface TravelerRepo extends JpaRepository<Traveler, Integer>{

}
