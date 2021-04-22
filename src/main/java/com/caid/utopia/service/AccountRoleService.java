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

import exception.RecordHasDependenciesException;
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
		
		/* Read account role by id */
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
		
		/* Delete account role */
		public void deleteAccountRole(AccountRole role) throws RecordNotFoundException {
			try {
				if(!accountRoleRepo.findById(role.getRoleId()).isPresent()) {
					throw new RecordNotFoundException();
				} else {
					AccountRole temp = accountRoleRepo.findById(role.getRoleId()).get();
					if(accountRepo.AccountRoleHasAccounts(temp).isEmpty()) {
						accountRoleRepo.delete(temp);
					} else {
						throw new RecordHasDependenciesException();
					}
				}
			} catch (Exception e) {
				throw new RecordNotFoundException();
			}
		}
	}