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
@Table(name = "tbl_travelers")
public class Traveler implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7585045462083179540L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "traveler_id")
	private Integer travelerId;
	
	@ManyToOne
	@JoinColumn(name = "account_number")
	private Account account;
	
	@Column(name = "first_name", length = 45)
	@NonNull
	private String firstName;
	
	@Column(name = "dob")
	@NonNull
	private LocalDate dob;
	
	@Column(name = "middle_name", length = 100)
	@NonNull
	private String middleName;
	
	@Column(name = "last_name", length = 45)
	@NonNull
	private String lastName;

	@Column(name = "gender", length = 1)
	@NonNull
	private String gender;

	@Column(name = "known_traveler_number", length = 45)
	@NonNull
	private String knownTravelerNumber;

	public Integer getTravelerId() {
		return travelerId;
	}

	public void setTravelerId(Integer travelerId) {
		this.travelerId = travelerId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getKnownTravelerNumber() {
		return knownTravelerNumber;
	}

	public void setKnownTravelerNumber(String knownTravelerNumber) {
		this.knownTravelerNumber = knownTravelerNumber;
	}

	
}
