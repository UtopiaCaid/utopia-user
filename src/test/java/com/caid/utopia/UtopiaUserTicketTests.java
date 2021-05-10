package com.caid.utopia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.caid.utopia.entity.Account;
import com.caid.utopia.entity.Flight;
import com.caid.utopia.entity.Payment;
import com.caid.utopia.entity.Ticket;
import com.caid.utopia.entity.Traveler;
import com.caid.utopia.entity.userRequestBody.AccountFlight;
import com.caid.utopia.entity.userRequestBody.TravelerFlight;


public class UtopiaUserTicketTests extends UtopiaUserApplicationTests {
	
	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}
	
	@Test
	@Transactional
	void CreateTicketTest() throws Exception {
		String uri = "/user/Ticket";
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
		String uri = "/user/Ticket";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertTrue(status == 200 || status == 204);
		String content = mvcResult.getResponse().getContentAsString();
		if(status == 200) {
			Ticket[] ticket = super.mapFromJson(content, Ticket[].class);
			assertTrue(ticket.length >= 0);
		}
	}
	
	@Test
	void ReadTicketByIdTest() throws Exception {
		String uri = "/user/Ticket/{id}";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri, 1).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertTrue(status == 200 || status == 204);
		if(status == 200) {
			String content = mvcResult.getResponse().getContentAsString();
			Ticket ticket = super.mapFromJson(content, Ticket.class);
			assertTrue(ticket != null);
		}

	}
	
	@Test
	@Transactional
	void UpdateTicketTest() throws Exception {
		String uri = "/user/Ticket";
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
		assertTrue(status == 202 || status == 400);
	}
	
	@Test
	@Transactional
	void DeleteTicketTest() throws Exception {
		String uri = "/user/Ticket";
		Ticket ticket = new Ticket();
		ticket.setTicketNo(1);
		String inputJson = super.mapToJson(ticket);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertTrue(status == 202 || status == 204);
	}
	
	@Test
	@Transactional
	void DeleteAllAccountFlightTicketsTest() throws Exception {
		String uri = "/user/Flight/Account/Tickets";
		AccountFlight body = new AccountFlight();
		Account account = new Account();
		Flight flight = new Flight();
		account.setAccountNumber(1);
		flight.setFlightNo(1);
		body.setAccount(account);
		body.setFlight(flight);
		String inputJson = super.mapToJson(body);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	@Transactional
	void DeleteAllTravelerTicketsTest() throws Exception {
		String uri = "/user/Flight/Traveler/Tickets";
		Traveler traveler = new Traveler();
		traveler.setTravelerId(1);
		String inputJson = super.mapToJson(traveler);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	@Transactional
	void DeleteTravelerFlightTicket() throws Exception {
		String uri = "/user/Flight/Traveler/Ticket";
		TravelerFlight body = new TravelerFlight();
		Traveler traveler = new Traveler();
		Flight flight = new Flight();
		traveler.setTravelerId(1);
		flight.setFlightNo(2);
		body.setTraveler(traveler);
		body.setFlight(flight);
		String inputJson = super.mapToJson(body);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertTrue(status == 200 || status == 204);
	}
	

}
