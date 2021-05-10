package com.caid.utopia;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.caid.utopia.entity.Account;
import com.caid.utopia.entity.AccountRole;
import com.caid.utopia.entity.Aircraft;
import com.caid.utopia.entity.AircraftType;
import com.caid.utopia.entity.Airport;
import com.caid.utopia.entity.Flight;
import com.caid.utopia.entity.Traveler;
import com.caid.utopia.entity.userRequestBody.AccountFlight;
import com.caid.utopia.entity.userRequestBody.TravelerFlight;

public class UtopiaUserServiceTests extends UtopiaUserApplicationTests {

	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}
	
	@Test
	@Transactional
	void CreateAccountTest() throws Exception {
		String uri = "/user/Account";
		Account account = new Account();
		AccountRole role = new AccountRole();
		role.setRoleId(1);
		account.setRole(role);
		account.setUsername("Test username");
		account.setPassword("93418414");
		account.setEmail("jfwie@test.com");
		String inputJson = super.mapToJson(account);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
	
	@Test
	public void flightRetrievalTest() throws Exception {
		String uri = "/user/flights";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void getAccountFlightHistoryTest() throws Exception {
		String uri = "/user/Account/Flight/History";
		Account account = new Account();
		account.setAccountNumber(1);
		String inputJson = super.mapToJson(account);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertTrue(status == 200 || status == 204);
	}
	
	@Test
	public void getAccountUpcomingFlightsTests() throws Exception {
		String uri = "/user/Account/Flight";
		Account account = new Account();
		account.setAccountNumber(1);
		String inputJson = super.mapToJson(account);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertTrue(status == 200 || status == 204);
	}
	
	@Test
	public void getAccountTicketsTest() throws Exception {
		String uri = "/user/Account/Ticket/History";
		Account account = new Account();
		account.setAccountNumber(1);
		String inputJson = super.mapToJson(account);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertTrue(status == 200 || status == 204);
	}
	
	@Test
	public void getAccountUpcomingTicketsTest() throws Exception {
		String uri = "/user/Account/Ticket";
		Account account = new Account();
		account.setAccountNumber(1);
		String inputJson = super.mapToJson(account);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertTrue(status == 200 || status == 204);
	}
	
	@Test
	public void getAccountTravelersTest() throws Exception {
		String uri = "/user/Account/Traveler";
		Account account = new Account();
		account.setAccountNumber(1);
		String inputJson = super.mapToJson(account);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void getFlightAvailableSeatsTest() throws Exception {
		String uri = "/user/Flight/Seats";
		Flight flight = new Flight();
		flight.setFlightNo(1);
		String inputJson = super.mapToJson(flight);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void deleteAllAccountFlightTicketsTest() throws Exception {
		String uri = "/user/Flight/Account/Tickets";
		AccountFlight accountFlight = new AccountFlight();
		Account account = new Account();
		Flight flight = new Flight();
		account.setAccountNumber(1);
		flight.setFlightNo(1);
		accountFlight.setAccount(account);
		accountFlight.setFlight(flight);
		String inputJson = super.mapToJson(accountFlight);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void deleteAllTravelerTicketsTest() throws Exception {
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
	public void deleteTravelerFlightTicketTest() throws Exception {
		String uri = "/user/Flight/Traveler/Ticket";
		TravelerFlight travelerFlight = new TravelerFlight();
		Traveler traveler = new Traveler();
		Flight flight = new Flight();
		traveler.setTravelerId(1);
		flight.setFlightNo(1);
		travelerFlight.setTraveler(traveler);
		travelerFlight.setFlight(flight);
		String inputJson = super.mapToJson(travelerFlight);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertTrue(status == 200 || status == 204);
	}
}
