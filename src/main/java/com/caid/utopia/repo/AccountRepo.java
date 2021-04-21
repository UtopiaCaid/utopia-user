package com.caid.utopia.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caid.utopia.entity.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer>{

}
