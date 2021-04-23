package com.caid.utopia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.caid.utopia.entity.Aircraft;
import com.caid.utopia.entity.AircraftType;


public class UtopiaUserAircraftTests extends UtopiaUserApplicationTests {
	
	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}
	
	@Test
	void ReadAircraftTest() throws Exception {
		String uri = "/user/Aircraft";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertTrue(status == 200 || status == 204);
		String content = mvcResult.getResponse().getContentAsString();
		Aircraft[] aircraft = super.mapFromJson(content, Aircraft[].class);
		assertTrue(aircraft.length >= 0);
	}
	
	@Test
	void ReadAircraftTypeTest() throws Exception {
		String uri = "/user/AircraftType";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertTrue(status == 200 || status == 204);
		String content = mvcResult.getResponse().getContentAsString();
		AircraftType[] aircraftType = super.mapFromJson(content, AircraftType[].class);
		assertTrue(aircraftType.length >= 0);
	}
	
	@Test
	void ReadAircraftByIdTest() throws Exception {
		String uri = "/user/Aircraft/{id}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri, 1).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertTrue(status == 200 || status == 204);
		String content = mvcResult.getResponse().getContentAsString();
		Aircraft aircraft = super.mapFromJson(content, Aircraft.class);
		assertTrue(aircraft != null);
	}
	
	@Test
	void ReadAircraftTypeByIdTest() throws Exception {
		String uri = "/user/AircraftType/{id}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri, 40).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertTrue(status == 200 || status == 204);
		String content = mvcResult.getResponse().getContentAsString();
		AircraftType aircraftType = super.mapFromJson(content, AircraftType.class);
		assertTrue(aircraftType != null);
	}
}
