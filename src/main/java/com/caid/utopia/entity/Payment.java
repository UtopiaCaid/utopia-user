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
 * @author ppradhan
 *
 */
@Entity
@Table(name = "tbl_payments")
public class Payment implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4333801077902139208L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private Integer paymentId;

	@ManyToOne
	@JoinColumn(name = "account_number")
	private Account account;

	@Column(name = "date_processed")
	@NonNull
	private LocalDate dateProcessed;

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public LocalDate getDateProcessed() {
		return dateProcessed;
	}

	public void setDateProcessed(LocalDate dateProcessed) {
		this.dateProcessed = dateProcessed;
	}
	
	public Account getAccount() {
		return account;
	}

	public void setAccounts(Account accounts) {
		this.account = accounts;
	}
	
}

