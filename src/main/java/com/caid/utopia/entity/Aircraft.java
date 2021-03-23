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
 * @author ppradhan
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

	public Integer getAircraftId() {
		return aircraftId;
	}

	public void setAircraftId(Integer aircraftId) {
		this.aircraftId = aircraftId;
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



	
}

