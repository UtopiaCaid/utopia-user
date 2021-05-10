package com.caid.utopia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.caid.utopia.entity.Account;
import com.caid.utopia.entity.Flight;
import com.caid.utopia.entity.Payment;
import com.caid.utopia.entity.Traveler;
import com.caid.utopia.entity.Traveler;


public class UtopiaUserTravelerTests extends UtopiaUserApplicationTests {
	
	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}
	
	@Test
	@Transactional
	void changeFlightTest() throws Exception {
		
	}
	
	@Test
	@Transactional
	void CreateTravelerTest() throws Exception {
		String uri = "/user/Traveler";
		Traveler traveler = new Traveler();
		Account account = new Account();
		account.setAccountNumber(1);
		String firstName = "firstopher";
		String lastName = "lastopher";
		LocalDate dob = LocalDate.now().minusYears(30);
		String gender = "T";
		String ktn = "198jfi38";
		traveler.setAccount(account);
		traveler.setFirstName(firstName);
		traveler.setLastName(lastName);
		traveler.setDob(dob);
		traveler.setGender(gender);
		traveler.setKnownTravelerNumber(ktn);
		String inputJson = super.mapToJson(traveler);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
	
	@Test
	void ReadTravelerTest() throws Exception {
		String uri = "/user/Traveler";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Traveler[] traveler = super.mapFromJson(content, Traveler[].class);
		assertTrue(traveler.length >= 0);
	}
	
	@Test
	void ReadTravelerByIdTest() throws Exception {
		String uri = "/user/Traveler/{id}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri, 1).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertTrue(status == 200 || status == 204);
		String content = mvcResult.getResponse().getContentAsString();
		Traveler traveler = super.mapFromJson(content, Traveler.class);
		assertTrue(traveler != null);
	}
	
	@Test
	@Transactional
	void UpdateTravelerTest() throws Exception {
		String uri = "/user/Traveler";
		Traveler traveler = new Traveler();
		traveler.setTravelerId(1);
		String middleName = "middlestopher";
		traveler.setMiddleName(middleName);
		String inputJson = super.mapToJson(traveler);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(202, status);
	}
	
}
