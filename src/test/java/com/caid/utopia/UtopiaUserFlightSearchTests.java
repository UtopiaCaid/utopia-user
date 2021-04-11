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

import com.caid.utopia.entity.Aircraft;
import com.caid.utopia.entity.AircraftType;
import com.caid.utopia.entity.Flight;
import com.caid.utopia.entity.flightSearch.OneWayBody;
import com.caid.utopia.entity.flightSearch.RoundTripBody;


public class UtopiaUserFlightSearchTests extends UtopiaUserApplicationTests {
	
	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}
	
	@Test
	void FindOneWayNoLayovers() throws Exception {
		String uri = "/OneWayNonLayover";
		OneWayBody body = new OneWayBody();
		body.setAirportDepId(1);
		body.setAirportArrId(2);
		body.setFlightDepBeginDate(LocalDate.parse("2021-04-01"));
		body.setFlightDepEndDate(LocalDate.parse("2021-04-20"));
		String inputJson = super.mapToJson(body);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Flight[] flights = super.mapFromJson(content, Flight[].class);
		assertTrue(flights.length >= 0);
	}
	
	@Test
	void FindOneWayLayovers() throws Exception {
		String uri = "/OneWayLayover";
		OneWayBody body = new OneWayBody();
		body.setAirportDepId(1);
		body.setAirportArrId(2);
		body.setFlightDepBeginDate(LocalDate.parse("2021-04-01"));
		body.setFlightDepEndDate(LocalDate.parse("2021-04-20"));
		String inputJson = super.mapToJson(body);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Flight[][] flights = super.mapFromJson(content, Flight[][].class);
		assertTrue(flights.length >= 0);
	}
	
	@Test
	void FindRoundTripNoLayovers() throws Exception {
		String uri = "/RoundTripNoLayover";
		RoundTripBody body = new RoundTripBody();
		body.setAirportDepId(1);
		body.setAirportArrId(2);
		body.setFlightDepBeginDate(LocalDate.parse("2021-04-01"));
		body.setFlightDepEndDate(LocalDate.parse("2021-04-20"));
		body.setFlightRetBeginDate(LocalDate.parse("2021-04-19"));
		body.setFlightRetEndDate(LocalDate.parse("2021-04-25"));
		String inputJson = super.mapToJson(body);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Flight[][] flights = super.mapFromJson(content, Flight[][].class);
		assertTrue(flights.length >= 0);
	}
	
	@Test
	void FindRoundTripLayovers() throws Exception {
		String uri = "/RoundTripLayovers";
		RoundTripBody body = new RoundTripBody();
		body.setAirportDepId(1);
		body.setAirportArrId(2);
		body.setFlightDepBeginDate(LocalDate.parse("2021-04-01"));
		body.setFlightDepEndDate(LocalDate.parse("2021-04-14"));
		body.setFlightRetBeginDate(LocalDate.parse("2021-04-15"));
		body.setFlightRetEndDate(LocalDate.parse("2021-04-25"));
		String inputJson = super.mapToJson(body);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Flight[][][] flights = super.mapFromJson(content, Flight[][][].class);
		assertTrue(flights.length >= 0);
	}
}
