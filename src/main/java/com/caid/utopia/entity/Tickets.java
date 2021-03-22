package com.caid.utopia.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class Tickets implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8386679197471411465L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_no")
	private Integer ticketNo;

	@ManyToOne
	@JoinColumn(name = "account_number")
	private Integer account_number;

	@OneToMany(mappedBy = "flight_no", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Aircraft> flightNo;
	
	@ManyToOne
	@JoinColumn(name = "traveler_id")
	private Integer travelerId;
	
	@ManyToOne
	@JoinColumn(name = "payment_id")
	private Integer paymentId;
	
	@Column(name = "confirmation_code")
	@NonNull
	private Integer confirmationCode;
	
	@Column(name = "ticket_price")
	@NonNull
	private float ticketPrice;
	
	@Column(name = "date_issued")
	@NonNull
	private LocalDate dateIssued;

	public Integer getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(Integer ticketNo) {
		this.ticketNo = ticketNo;
	}

	public Integer getAccount_number() {
		return account_number;
	}

	public void setAccount_number(Integer account_number) {
		this.account_number = account_number;
	}

	public List<Aircraft> getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(List<Aircraft> flightNo) {
		this.flightNo = flightNo;
	}

	public Integer getTravelerId() {
		return travelerId;
	}

	public void setTravelerId(Integer travelerId) {
		this.travelerId = travelerId;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public Integer getConfirmationCode() {
		return confirmationCode;
	}

	public void setConfirmationCode(Integer confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

	public float getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(float ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public LocalDate getDateIssued() {
		return dateIssued;
	}

	public void setDateIssued(LocalDate dateIssued) {
		this.dateIssued = dateIssued;
	}

	
	
}
