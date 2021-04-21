package com.caid.utopia.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caid.utopia.entity.Ticket;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Integer>{

}
