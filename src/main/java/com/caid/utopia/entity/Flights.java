/**
 * 
 */
package com.caid.utopia.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;


/**
 * @author dwoo
 *
 */
@Entity
@Table(name = "tbl_flights")
public class Flights implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3634923359976670872L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "flight_no")
	private Integer flightNo;

	@Column(name = "flight_gate", length = 45)
	@NonNull
	private String flightGate;
	
	@ManyToOne
	@JoinColumn(name = "airport_id_dep")
	private AircraftType airportIdDeparture;
	
	@ManyToOne
	@JoinColumn(name = "airport_id_arr")
	private AircraftType airportIdArrival;
	
	@ManyToOne
	@JoinColumn(name = "aircraft_id")
	private Integer aircraftId;
	
	@Column(name = "basePrice")
	@NonNull
	private float basePrice;
	
	@Column(name = "departure")
	@NonNull
	private LocalDate departure;
	
	@Column(name = "arrival")
	@NonNull
	private LocalDate arrival;
	
	@Column(name = "status", length = 45)
	@NonNull
	private String status;

	public Integer getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(Integer flightNo) {
		this.flightNo = flightNo;
	}

	public String getFlightGate() {
		return flightGate;
	}

	public void setFlightGate(String flightGate) {
		this.flightGate = flightGate;
	}

	public AircraftType getAirportIdDeparture() {
		return airportIdDeparture;
	}

	public void setAirportIdDeparture(AircraftType airportIdDeparture) {
		this.airportIdDeparture = airportIdDeparture;
	}

	public AircraftType getAirportIdArrival() {
		return airportIdArrival;
	}

	public void setAirportIdArrival(AircraftType airportIdArrival) {
		this.airportIdArrival = airportIdArrival;
	}

	public Integer getAircraftId() {
		return aircraftId;
	}

	public void setAircraftId(Integer aircraftId) {
		this.aircraftId = aircraftId;
	}

	public float getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}

	public LocalDate getDeparture() {
		return departure;
	}

	public void setDeparture(LocalDate departure) {
		this.departure = departure;
	}

	public LocalDate getArrival() {
		return arrival;
	}

	public void setArrival(LocalDate arrival) {
		this.arrival = arrival;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
