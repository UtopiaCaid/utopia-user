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


public class UtopiaAdminFlightSearchTests extends UtopiaAdminApplicationTests {
	
	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}
	
	/* Controller Tests */
	@Test
	@Transactional
	void ChangeAirplaneStatus() throws Exception {
		AircraftType aircraftType = new AircraftType();
		String uri = "/AircraftType";
		aircraftType.setAircraftType(-1);
		aircraftType.setaircraftTypeName("testAircraftType");
		aircraftType.setSeatMaximum(100);
		aircraftType.setManufacturer("testManufacturer");
		String inputJson = super.mapToJson(aircraftType);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		Aircraft aircraft = new Aircraft();
		aircraft.setAircraftType(aircraftType);
		aircraft.setSeatCount(100);
		aircraft.setFirstClassCount(20);
		aircraft.setSecondClassCount(20);
		aircraft.setThirdClassCount(20);
		aircraft.setAircraftStatus("Active");
		uri = "/Aircraft";
		inputJson = super.mapToJson(aircraft);
		mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		aircraft = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Aircraft.class);
		/* deactivate */
		uri = "/Aircraft/Deactivate";
		inputJson = super.mapToJson(aircraft);
		mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		status = mvcResult.getResponse().getStatus();
		assertEquals(202, status);
		/* activate */
		uri = "/Aircraft/Activate";
		mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		status = mvcResult.getResponse().getStatus();
		assertEquals(202, status);
	}
	
	@Test
	@Transactional
	void CreateAircraftTest() throws Exception {
		String uri = "/AircraftType";
		AircraftType aircraftType = new AircraftType();
		aircraftType.setAircraftType(-1);
		aircraftType.setaircraftTypeName("testAircraftType");
		aircraftType.setSeatMaximum(100);
		aircraftType.setManufacturer("testManufacturer");
		String inputJson = super.mapToJson(aircraftType);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		Aircraft aircraft = new Aircraft();
		aircraft.setAircraftType(aircraftType);
		aircraft.setSeatCount(100);
		aircraft.setFirstClassCount(20);
		aircraft.setSecondClassCount(20);
		aircraft.setThirdClassCount(20);
		aircraft.setAircraftStatus("Active");
		uri = "/Aircraft";
		inputJson = super.mapToJson(aircraft);
		mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
	@Test
	@Transactional
	void CreateAircraftTypeTest() throws Exception {
		String uri = "/AircraftType";
		AircraftType aircraftType = new AircraftType();
		aircraftType.setAircraftType(-1);
		aircraftType.setaircraftTypeName("testAircraftType");
		aircraftType.setSeatMaximum(100);
		aircraftType.setManufacturer("testManufacturer");
		String inputJson = super.mapToJson(aircraftType);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
	
	@Test
	void ReadAircraftTest() throws Exception {
		String uri = "/Aircraft";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Aircraft[] aircraft = super.mapFromJson(content, Aircraft[].class);
		assertTrue(aircraft.length >= 0);
	}
	
	@Test
	void ReadAircraftTypeTest() throws Exception {
		String uri = "/AircraftType";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		AircraftType[] aircraftType = super.mapFromJson(content, AircraftType[].class);
		assertTrue(aircraftType.length >= 0);
	}
	
	/* Service Tests */
	
}
