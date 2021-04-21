/**
 * 
 */
package com.caid.utopia.entity;

import java.io.Serializable;

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
@Table(name = "tbl_aircraft")
public class Aircraft implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -3662677232024821777L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "aircraft_id")
	private Integer aircraftId;
	
	@Column(name = "seat_count")
	@NonNull
	private Integer seatCount;

	@Column(name = "first_class_count")
	@NonNull
	private Integer firstClassCount;

	@Column(name = "second_class_count")
	@NonNull
	private Integer secondClassCount;
	
	@Column(name = "third_class_count")
	@NonNull
	private Integer thirdClassCount;
	
	@ManyToOne
	@JoinColumn(name = "aircraft_type_id")
	private AircraftType aircraftType;
	
	@Column(name = "aircraft_status")
	private String aircraftStatus;

	public String getAircraftStatus() {
		return aircraftStatus;
	}

	public void setAircraftStatus(String aircraftStatus) {
		this.aircraftStatus = aircraftStatus;
	}

	public Integer getAircraftId() {
		return aircraftId;
	}

	public void setAircraftId(Integer aircraftId) {
		this.aircraftId = aircraftId;
	}

	public Integer getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(Integer seatCount) {
		this.seatCount = seatCount;
	}
	
	public Integer getFirstClassCount() {
		return firstClassCount;
	}

	public void setFirstClassCount(Integer firstClassCount) {
		this.firstClassCount = firstClassCount;
	}

	public Integer getSecondClassCount() {
		return secondClassCount;
	}

	public void setSecondClassCount(Integer secondClassCount) {
		this.secondClassCount = secondClassCount;
	}

	public Integer getThirdClassCount() {
		return thirdClassCount;
	}

	public void setThirdClassCount(Integer thirdClassCount) {
		this.thirdClassCount = thirdClassCount;
	}

	public AircraftType getAircraftType() {
		return aircraftType;
	}

	public void setAircraftType(AircraftType aircraftType) {
		this.aircraftType = aircraftType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aircraftType == null) ? 0 : aircraftType.hashCode());
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
		Aircraft other = (Aircraft) obj;
		if (aircraftType == null) {
			if (other.aircraftType != null)
				return false;
		} else if (!aircraftType.equals(other.aircraftType))
			return false;
		return true;
	}

}

