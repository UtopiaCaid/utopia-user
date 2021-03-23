package com.caid.utopia.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caid.utopia.entity.Tickets;

@Repository
public interface TicketsRepo extends JpaRepository<Tickets, Integer>{

}
