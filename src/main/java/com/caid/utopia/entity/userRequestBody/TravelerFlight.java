package com.caid.utopia.entity.userRequestBody;

import com.caid.utopia.entity.Flight;
import com.caid.utopia.entity.Traveler;

public class TravelerFlight {
	Traveler traveler;
	Flight flight;
	public Traveler getTraveler() {
		return traveler;
	}
	public void setTraveler(Traveler traveler) {
		this.traveler = traveler;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
}
