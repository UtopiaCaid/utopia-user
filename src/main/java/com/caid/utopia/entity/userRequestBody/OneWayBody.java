package com.caid.utopia.entity.userRequestBody;

import java.time.LocalDate;

import com.caid.utopia.entity.Airport;

public class OneWayBody {
	private Integer airportDepId;
	private Integer airportArrId;
	private LocalDate flightDepBeginDate;
	private LocalDate flightDepEndDate;
	
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
}
