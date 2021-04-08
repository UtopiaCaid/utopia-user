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


public class UtopiaAdminTravelerTests extends UtopiaAdminApplicationTests {
	
	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}
	
	/* Controller Tests */
	@Test
	@Transactional
	void CreateTravelerTest() throws Exception {
		String uri = "/Traveler";
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
		String uri = "/Traveler";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Traveler[] traveler = super.mapFromJson(content, Traveler[].class);
		assertTrue(traveler.length >= 0);
	}
	
	@Test
	@Transactional
	void UpdateTravelerTest() throws Exception {
		String uri = "/Traveler";
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
	
	@Test
	@Transactional
	void DeleteTravelerTest() throws Exception {
		String uri = "/Traveler";
		Traveler traveler = new Traveler();
		traveler.setTravelerId(1);
		Account account = new Account();
		account.setAccountNumber(1);
		String inputJson = super.mapToJson(traveler);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(422,status); // dependency exception
	}
}
