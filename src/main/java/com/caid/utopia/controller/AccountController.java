package com.caid.utopia.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caid.utopia.entity.Flight;
import com.caid.utopia.entity.Account;
import com.caid.utopia.service.AccountService;
import exception.RecordNotFoundException;
import exception.RecordCreationException;
import exception.RecordForeignKeyConstraintException;
import exception.ExceptionReducer;
import exception.RecordAlreadyExistsException;
import exception.RecordUpdateException;
import exception.RecordHasDependenciesException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AccountController {
	

	@Autowired
	AccountService accountService;
	
	@ExceptionHandler({
		RecordNotFoundException.class, //404
		RecordCreationException.class, //404
		RecordForeignKeyConstraintException.class, //409
		RecordAlreadyExistsException.class, //409
		RecordUpdateException.class, //400
		RecordHasDependenciesException.class //422
	})
	@Nullable
	public final ResponseEntity<Object> handleException(Exception ex) throws Exception {
		return ExceptionReducer.handleException(ex);
	}
	
	
	/* get all records*/
	@RequestMapping(value = "/Account", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Account>> getAllAccount(){
		List<Account> account = accountService.getAllAccounts();
		System.out.println(account.size());
		if( account.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(account, HttpStatus.OK);
		}
	}
	
	/* get record by id */
	@RequestMapping(value = "/Account/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Account> getAccountById(@PathVariable Integer id){
		Account account = accountService.getAccountById(id);
		if(account.getAccountNumber() != id) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(account, HttpStatus.OK);
		}	
	}
	
	
	/* create record */
	@Transactional
	@RequestMapping(value = "/Account", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> createAccount(@RequestBody Account account) throws Exception {
		try {
			if(accountService.createAccount(account) instanceof Account) {
				return new ResponseEntity<>(account, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	/* update record */
	@Transactional
	@RequestMapping(value = "/Account", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> updateAccount(@RequestBody Account account) throws Exception {
		try {
			if(accountService.updateAccount(account) instanceof Account) {
				return new ResponseEntity<>(account, HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	/* delete record */
	/*
	@Transactional
	@RequestMapping(value = "/Account", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> deleteAccount(@RequestBody Account account) throws Exception {
		try {
			accountService.deleteAccount(account);
			return new ResponseEntity<>(account, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return handleException(e);
		}
	}
	*/
	
	/* deactivate record */
	@Transactional
	@RequestMapping(value = "/Account/Deactivation", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> deactivateAccount(@RequestBody Account account) throws Exception {
		try {
			if(accountService.deactivateAccount	(account) instanceof Account) {
				return new ResponseEntity<>(account, HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
}