package com.caid.utopia.entity.userRequestBody;

import java.time.LocalDateTime;

public class RoundTripBody {
	private Integer airportDepId;
	private Integer airportArrId;
	private LocalDateTime flightDepBeginDate;
	private LocalDateTime flightDepEndDate;
	private LocalDateTime flightRetBeginDate;
	private LocalDateTime flightRetEndDate;
	
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
	public void setFlightRetBeginDate(LocalDateTime flightRetBeginDate) {
		this.flightRetBeginDate = flightRetBeginDate;
	}
	public LocalDateTime getFlightRetEndDate() {
		return flightRetEndDate;
	}
	public LocalDateTime getFlightDepEndDate() {
		return flightDepEndDate;
	}
	public void setFlightDepEndDate(LocalDateTime flightDepEndDate) {
		this.flightDepEndDate = flightDepEndDate;
	}
	public LocalDateTime getFlightRetBeginDate() {
		return flightRetBeginDate;
	}
	public void setFlightDepBeginDate(LocalDateTime flightDepBeginDate) {
		this.flightDepBeginDate = flightDepBeginDate;
	}
	public void setFlightRetEndDate(LocalDateTime flightRetEndDate) {
		this.flightRetEndDate = flightRetEndDate;
	}
}

