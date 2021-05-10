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
	
	@Test
	void ReadAirportTest() throws Exception {
		String uri = "/user/Airport";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Airport[] airport = super.mapFromJson(content, Airport[].class);
		assertTrue(airport.length >= 0);
	}
	
	@Test
	void ReadAirportByIdTest() throws Exception {
		String uri = "/user/Airport/{id}";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri, 1).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertTrue(status == 200 || status == 204);
		String content = mvcResult.getResponse().getContentAsString();
		Airport airport = super.mapFromJson(content, Airport.class);
		assertTrue(airport != null);
	}
}