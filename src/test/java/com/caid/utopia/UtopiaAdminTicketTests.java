package com.caid.utopia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.caid.utopia.entity.Flight;
import com.caid.utopia.entity.Payment;
import com.caid.utopia.entity.Ticket;
import com.caid.utopia.entity.Traveler;


public class UtopiaAdminTicketTests extends UtopiaAdminApplicationTests {
	
	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}
	
	/* Controller Tests */
	@Test
	@Transactional
	void CreateTicketTest() throws Exception {
		String uri = "/Ticket";
		Ticket ticket = new Ticket();
		Flight flight = new Flight();
		flight.setFlightNo(1);
		Traveler traveler = new Traveler();
		traveler.setTravelerId(1);
		Payment payment = new Payment();
		payment.setPaymentId(1);
		ticket.setFlight(flight);
		ticket.setTraveler(traveler);
		ticket.setPayment(payment);
		ticket.setConfirmationCode(73145);
		ticket.setTicketPrice(5);
		String inputJson = super.mapToJson(ticket);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
	
	@Test
	void ReadTicketTest() throws Exception {
		String uri = "/Ticket";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Ticket[] ticket = super.mapFromJson(content, Ticket[].class);
		assertTrue(ticket.length >= 0);
	}
	
	@Test
	@Transactional
	void UpdateTicketTest() throws Exception {
		String uri = "/Ticket";
		Ticket ticket = new Ticket();
		ticket.setTicketNo(1);
		Flight flight = new Flight();
		flight.setFlightNo(1);
		Traveler traveler = new Traveler();
		traveler.setTravelerId(1);
		Payment payment = new Payment();
		payment.setPaymentId(1);
		ticket.setFlight(flight);
		ticket.setTraveler(traveler);
		ticket.setPayment(payment);
		String inputJson = super.mapToJson(ticket);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(202, status);
	}
	
	@Test
	@Transactional
	void DeleteTicketTest() throws Exception {
		String uri = "/Ticket";
		Ticket ticket = new Ticket();
		Flight flight = new Flight();
		flight.setFlightNo(1);
		Traveler traveler = new Traveler();
		traveler.setTravelerId(1);
		Payment payment = new Payment();
		payment.setPaymentId(1);
		ticket.setFlight(flight);
		ticket.setTraveler(traveler);
		ticket.setPayment(payment);
		ticket.setConfirmationCode(73145);
		ticket.setTicketPrice(5);
		String inputJson = super.mapToJson(ticket);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		inputJson = mvcResult.getResponse().getContentAsString();
		mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		status = mvcResult.getResponse().getStatus();
		assertEquals(202,status);
	}
}
