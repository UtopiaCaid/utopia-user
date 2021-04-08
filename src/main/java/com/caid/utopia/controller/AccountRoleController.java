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
import com.caid.utopia.entity.AccountRole;
import com.caid.utopia.service.AccountRoleService;
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
public class AccountRoleController {
	

	@Autowired
	AccountRoleService accountRoleService;
	
	@ExceptionHandler({
		RecordNotFoundException.class, //404
		RecordCreationException.class, //404
		RecordForeignKeyConstraintException.class, //409
		RecordAlreadyExistsException.class, //409
		RecordUpdateException.class, //400
		RecordHasDependenciesException.class, //422
	})
	@Nullable
	public final ResponseEntity<Object> handleException(Exception ex) throws Exception {
		return ExceptionReducer.handleException(ex);
	}
	
	
	/* get all records*/
	@RequestMapping(value = "/AccountRole", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<AccountRole>> getAllAccountRoles(){
		List<AccountRole> role = accountRoleService.getAllAccountRoles();
		System.out.println(role.size());
		if( role.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(role, HttpStatus.OK);
		}
	}
	
	/* get record by id */
	@RequestMapping(value = "/AccountRole/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<AccountRole> getAccountRoleById(@PathVariable Integer id){
		AccountRole role = accountRoleService.getAccountRoleById(id);
		if(role.getRoleId() != id) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(role, HttpStatus.OK);
		}	
	}
	
	/* delete record */
	@RequestMapping(value = "/AccountRole", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<AccountRole> deleteAccountRole(@RequestBody AccountRole role){
		AccountRole temp = accountRoleService.getAccountRoleById(role.getRoleId());
		if(temp == null) {
			throw new RecordNotFoundException();
		} else {
			accountRoleService.deleteAccountRole(temp);
		}
		return new ResponseEntity<>(temp, HttpStatus.OK);

	}
}