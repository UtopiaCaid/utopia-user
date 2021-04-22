package com.caid.utopia.entity.userRequestBody;

import java.time.LocalDateTime;

import com.caid.utopia.entity.Airport;

public class OneWayBody {
	private Integer airportDepId;
	private Integer airportArrId;
	private LocalDateTime flightDepBeginDate;
	private LocalDateTime flightDepEndDate;
	
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
	public LocalDateTime getFlightDepBeginDate() {
		return flightDepBeginDate;
	}
	public void setFlightDepBeginDate(LocalDateTime flightDepBeginDate) {
		this.flightDepBeginDate = flightDepBeginDate;
	}
	public LocalDateTime getFlightDepEndDate() {
		return flightDepEndDate;
	}
	public void setFlightDepEndDate(LocalDateTime flightDepEndDate) {
		this.flightDepEndDate = flightDepEndDate;
	}
}
