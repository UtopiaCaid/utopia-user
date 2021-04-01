package com.caid.utopia.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.caid.utopia.entity.AccountRole;
import com.caid.utopia.repo.AccountRepo;
import com.caid.utopia.repo.AccountRoleRepo;
import com.caid.utopia.repo.FlightRepo;
import com.caid.utopia.repo.PaymentRepo;

import exception.RecordNotFoundException;


	@Service
	public class AccountRoleService {
		
		@Autowired
		FlightRepo flightsRepo;
		
		@Autowired
		PaymentRepo paymentsRepo;
		
		@Autowired
		AccountRepo accountRepo;
		
		@Autowired
		AccountRoleRepo accountRoleRepo;
					
		/* Read all account roles */
		public List<AccountRole> getAllAccountRoles() throws RecordNotFoundException {
			
			try {
				List<AccountRole> role = accountRoleRepo.findAll();
				return role;
			} catch (Exception e) {
				throw new RecordNotFoundException();
			}		
		}
		
		/* Read account by id */
		public AccountRole getAccountRoleById(Integer id) throws RecordNotFoundException {
			try {
				Optional<AccountRole> role = accountRoleRepo.findById(id);
				if(role.isPresent()) {
					return role.get();
				} else {
					throw new RecordNotFoundException();
				}
			}catch(Exception e) {
				throw new RecordNotFoundException();
			}
		}
	}