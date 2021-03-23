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
@Table(name = "tbl_aircraft_type")
public class AircraftType implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2321331676252257491L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "aircraft_type_id")
	private Integer aircraftTypeId;

	@Column(name = "aircraft_type_name", length = 45)
	@NonNull
	private String aircraftTypeName;
	
	@Column(name = "seat_maximum")
	@NonNull
	private Integer seatMaximum;
	
	@Column(name = "manufacturer", length = 45)
	@NonNull
	private String manufacturer;

	@OneToMany(mappedBy = "aircraftType", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Aircraft> aircraft;
	
	public Integer getAircraftTypeId() {
		return aircraftTypeId;
	}

	public void setAircraftType(Integer aircraftTypeId) {
		this.aircraftTypeId = aircraftTypeId;
	}

	public String getaircraftTypeName() {
		return aircraftTypeName;
	}

	public void setaircraftTypeName(String aircraftTypeName) {
		this.aircraftTypeName = aircraftTypeName;
	}
	
	public Integer getSeatMaximum() {
		return seatMaximum;
	}

	public void setSeatMaximum(Integer seatMaximum) {
		this.seatMaximum = seatMaximum;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public List<Aircraft> getAircraft() {
		return aircraft;
	}

	public void setAircraft(List<Aircraft> aircraft) {
		this.aircraft = aircraft;
	}
}
