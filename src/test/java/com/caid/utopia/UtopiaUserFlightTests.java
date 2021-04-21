package com.caid.utopia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.caid.utopia.entity.Aircraft;
import com.caid.utopia.entity.AircraftType;
import com.caid.utopia.entity.Airport;
import com.caid.utopia.entity.Flight;

public class UtopiaUserFlightTests extends UtopiaUserApplicationTests {

	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}

	@Test
	@Transactional
	public void flightRetrievalTest() throws Exception {
		String uri = "/user/flights";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	
}