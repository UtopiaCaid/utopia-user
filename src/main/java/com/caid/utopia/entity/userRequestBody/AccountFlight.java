package com.caid.utopia.entity.userRequestBody;

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
