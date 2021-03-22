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
		
	@Column(name = "seatCount")
	@NonNull
	private Integer first_class_count;

	@Column(name = "seatCount")
	@NonNull
	private Integer second_class_count;
	
	@Column(name = "seatCount")
	@NonNull
	private Integer third_class_count;
	
	@ManyToOne
	@JoinColumn(name = "aircraft_type_id")
	private AircraftType aircraftType;

	public Integer getAircraftId() {
		return aircraftId;
	}

	public void setAircraftId(Integer aircraftId) {
		this.aircraftId = aircraftId;
	}

	public Integer getFirst_class_count() {
		return first_class_count;
	}

	public void setFirst_class_count(Integer first_class_count) {
		this.first_class_count = first_class_count;
	}

	public Integer getSecond_class_count() {
		return second_class_count;
	}

	public void setSecond_class_count(Integer second_class_count) {
		this.second_class_count = second_class_count;
	}

	public Integer getThird_class_count() {
		return third_class_count;
	}

	public void setThird_class_count(Integer third_class_count) {
		this.third_class_count = third_class_count;
	}

	public AircraftType getAircraftType() {
		return aircraftType;
	}

	public void setAircraftType(AircraftType aircraftType) {
		this.aircraftType = aircraftType;
	}

	
}

