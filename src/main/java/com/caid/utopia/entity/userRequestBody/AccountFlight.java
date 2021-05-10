package com.caid.utopia.entity.userRequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.caid.utopia.entity.Account;
import com.caid.utopia.entity.Flight;

public class AccountFlight {
	
	Account account;
	Flight flight;
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
}
