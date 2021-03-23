package com.caid.utopia.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caid.utopia.entity.Travelers;

@Repository
public interface TravelersRepo extends JpaRepository<Travelers, Integer>{

}
