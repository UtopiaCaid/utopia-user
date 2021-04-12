package com.caid.utopia.entity;

import java.io.Serializable;
import java.time.LocalDate;
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

@Entity
@Table(name = "tbl_tickets")
public class Ticket implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8386679197471411465L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_no")
	private Integer ticketNo;

	@ManyToOne
	@JoinColumn(name = "flight_no")
	private Flight flight;
	
	@ManyToOne
	@JoinColumn(name = "traveler_id")
	private Traveler traveler;
	
	@ManyToOne
	@JoinColumn(name = "payment_id")
	private Payment payment;
	
	@Column(name = "confirmation_code")
	@NonNull
	private Integer confirmationCode;
	
	@Column(name = "ticket_price")
	@NonNull
	private float ticketPrice;
	
	@Column(name = "ticket_class")
	private Integer ticketClass;
	
	public Integer getTicketClass() {
		return ticketClass;
	}

	public void setTicketClass(Integer ticketClass) {
		this.ticketClass = ticketClass;
	}

	@Column(name = "date_issued")
	@NonNull
	private LocalDate dateIssued;

	public Integer getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(Integer ticketNo) {
		this.ticketNo = ticketNo;
	}


	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Traveler getTraveler() {
		return traveler;
	}

	public void setTraveler(Traveler traveler) {
		this.traveler = traveler;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
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
