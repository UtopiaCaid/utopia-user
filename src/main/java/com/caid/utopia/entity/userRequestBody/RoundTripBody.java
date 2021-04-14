package com.caid.utopia.entity.userRequestBody;

import java.time.LocalDate;

public class RoundTripBody {
	private Integer airportDepId;
	private Integer airportArrId;
	private LocalDate flightDepBeginDate;
	private LocalDate flightDepEndDate;
	private LocalDate flightRetBeginDate;
	private LocalDate flightRetEndDate;
	
	public Integer getAirportDepId() {
		return airportDepId;
	}
	public void setAirportDepId(Integer airportDepId) {
		this.airportDepId = airportDepId;
	}
	public Integer getAirportArrId() {
		return airportArrId;
	}
	public void setAirportArrId(Integer airportArrId) {
		this.airportArrId = airportArrId;
	}
	public LocalDate getFlightDepBeginDate() {
		return flightDepBeginDate;
	}
	public void setFlightDepBeginDate(LocalDate flightDepBeginDate) {
		this.flightDepBeginDate = flightDepBeginDate;
	}
	public LocalDate getFlightDepEndDate() {
		return flightDepEndDate;
	}
	public void setFlightDepEndDate(LocalDate flightDepEndDate) {
		this.flightDepEndDate = flightDepEndDate;
	}
	
	public LocalDate getFlightRetBeginDate() {
		return flightRetBeginDate;
	}
	public void setFlightRetBeginDate(LocalDate flightRetBeginDate) {
		this.flightRetBeginDate = flightRetBeginDate;
	}
	public LocalDate getFlightRetEndDate() {
		return flightRetEndDate;
	}
	public void setFlightRetEndDate(LocalDate flightRetEndDate) {
		this.flightRetEndDate = flightRetEndDate;
	}
}
