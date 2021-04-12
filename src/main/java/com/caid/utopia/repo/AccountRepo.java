package com.caid.utopia.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.caid.utopia.entity.Account;
import com.caid.utopia.entity.AccountRole;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer>{
	@Query("FROM Account WHERE role = :curr ")
	List<Account> AccountRoleHasAccounts(@Param("curr") AccountRole curr);
	
}
