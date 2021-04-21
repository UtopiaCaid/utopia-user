/**
 * 
 */
package com.caid.utopia.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * @author dwoo
 *
 */
@Entity
@Table(name = "tbl_flights")
public class Flight implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6063208213215353024L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "flight_no")
	private Integer flightNo;

	@Column(name = "flight_gate", length = 45)
	@NonNull
	private String flightGate;
	
	@ManyToOne
	@JoinColumn(name = "airport_id_dep")
	private Airport airportDeparture;
	
	@ManyToOne
	@JoinColumn(name = "airport_id_arr")
	private Airport airportArrival;
	
	@ManyToOne
	@JoinColumn(name = "aircraft_id")
	private Aircraft aircraft;
	
	@OneToMany(mappedBy = "flight", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Ticket> tickets;
	

	@Column(name = "base_price")
	@NonNull
	private float basePrice;
	
	@Column(name = "departure")
	@NonNull
	private LocalDateTime departure;
	
	@Column(name = "arrival")
	@NonNull
	private LocalDateTime arrival;
	
	@Column(name = "status", length = 45)
	@NonNull
	private String status;
	
	public Aircraft getAircraft() {
		return aircraft;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

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

	public Airport getairportDeparture() {
		return airportDeparture;
	}

	public void setairportDeparture(Airport airportDeparture) {
		this.airportDeparture = airportDeparture;
	}

	public Airport getairportArrival() {
		return airportArrival;
	}

	public void setairportArrival(Airport airportArrival) {
		this.airportArrival = airportArrival;
	}



	public float getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}

	public LocalDateTime getDeparture() {
		return departure;
	}

	public void setDeparture(LocalDateTime departure) {
		this.departure = departure;
	}

	public LocalDateTime getArrival() {
		return arrival;
	}

	public void setArrival(LocalDateTime arrival) {
		this.arrival = arrival;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
