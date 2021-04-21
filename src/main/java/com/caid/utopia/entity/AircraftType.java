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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aircraft == null) ? 0 : aircraft.hashCode());
		result = prime * result + ((aircraftTypeId == null) ? 0 : aircraftTypeId.hashCode());
		result = prime * result + ((aircraftTypeName == null) ? 0 : aircraftTypeName.hashCode());
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((seatMaximum == null) ? 0 : seatMaximum.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AircraftType other = (AircraftType) obj;
		if (aircraft == null) {
			if (other.aircraft != null)
				return false;
		} else if (!aircraft.equals(other.aircraft))
			return false;
		if (aircraftTypeId == null) {
			if (other.aircraftTypeId != null)
				return false;
		} else if (!aircraftTypeId.equals(other.aircraftTypeId))
			return false;
		if (aircraftTypeName == null) {
			if (other.aircraftTypeName != null)
				return false;
		} else if (!aircraftTypeName.equals(other.aircraftTypeName))
			return false;
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
			return false;
		if (seatMaximum == null) {
			if (other.seatMaximum != null)
				return false;
		} else if (!seatMaximum.equals(other.seatMaximum))
			return false;
		return true;
	}

	
}
