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
import com.caid.utopia.entity.Airport;
import com.caid.utopia.entity.Traveler;


public class UtopiaUserAirportTests extends UtopiaUserApplicationTests {
	
	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}
	
	/* Controller Tests */
	@Test
	@Transactional
	void CreateAirportTest() throws Exception {
		String uri = "/Airport";
		Airport airport = new Airport();
		airport.setAirportCode(12345);
		airport.setAirportName("Airport Name Test");
		airport.setCity("Airport City Test");
		airport.setStatus("Active");
		String inputJson = super.mapToJson(airport);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
	
	@Test
	void ReadAirportTest() throws Exception {
		String uri = "/Airport";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Airport[] airport = super.mapFromJson(content, Airport[].class);
		assertTrue(airport.length >= 0);
	}
	
}