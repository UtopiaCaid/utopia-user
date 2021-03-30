/**
 * 
 */
package com.caid.utopia.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author dwoo
 *
 */
@Entity
@Table(name = "tbl_airports")
public class Airport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7995772900653995693L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "airport_id")
	private Integer airportId;

	@Column(name = "airport_code")
	@NonNull
	private Integer airportCode;
	
	@Column(name = "city", length = 45)
	@NonNull
	private String city;
	
	@Column(name = "airport_name", length = 45)
	@NonNull
	private String airportName;
	
	@Column(name = "status", length = 45)
	@NonNull
	private String status;

	@OneToMany(mappedBy = "airportArrival", fetch = FetchType.LAZY)
	@JsonBackReference(value = "arrival")
	private List<Flight> arrivalFlights;

	@OneToMany(mappedBy = "airportDeparture", fetch = FetchType.LAZY)
	@JsonBackReference(value = "departure")
	private List<Flight> departureFlights;

	public Integer getAirportId() {
		return airportId;
	}

	public void setAirportId(Integer airportId) {
		this.airportId = airportId;
	}

	public Integer getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(Integer airportCode) {
		this.airportCode = airportCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Flight> getArrivalFlights() {
		return arrivalFlights;
	}

	public void setArrivalFlights(List<Flight> arrivalFlights) {
		this.arrivalFlights = arrivalFlights;
	}

	public List<Flight> getDepartureFlights() {
		return departureFlights;
	}

	public void setDepartureFlights(List<Flight> departureFlights) {
		this.departureFlights = departureFlights;
	}

	
}
