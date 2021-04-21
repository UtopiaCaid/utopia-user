package com.caid.utopia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.caid.utopia.entity.Flight;
import com.caid.utopia.entity.userRequestBody.OneWayBody;
import com.caid.utopia.entity.userRequestBody.RoundTripBody;


public class UtopiaUserFlightSearchTests extends UtopiaUserApplicationTests {
	
	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}
	

	@Test
	void FindOneWayNoLayovers() throws Exception {
		String uri = "/user/OneWayNonLayover";
		OneWayBody body = new OneWayBody();
		body.setAirportDepId(1);
		body.setAirportArrId(2);
		body.setFlightDepBeginDate(LocalDateTime.of(2021,4,1,0,0,0));
		body.setFlightDepEndDate(LocalDateTime.of(2021,4,21,0,0,0));
		String inputJson = super.mapToJson(body);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
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
		String uri = "/user/OneWayAllLayover";
		OneWayBody body = new OneWayBody();
		body.setAirportDepId(1);
		body.setAirportArrId(2);
		body.setFlightDepBeginDate(LocalDateTime.of(2021,4,1,0,0,0));
		body.setFlightDepEndDate(LocalDateTime.of(2021,4,21,0,0,0));
		String inputJson = super.mapToJson(body);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
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
		String uri = "/user/RoundTripNoLayover";
		RoundTripBody body = new RoundTripBody();
		body.setAirportDepId(1);
		body.setAirportArrId(2);
		body.setFlightDepBeginDate(LocalDateTime.of(2021,4,1,0,0,0));
		body.setFlightDepEndDate(LocalDateTime.of(2021,4,21,0,0,0));
		body.setFlightRetBeginDate(LocalDateTime.of(2021,4,19,0,0,0));
		body.setFlightRetEndDate(LocalDateTime.of(2021,4,25,0,0,0));
		String inputJson = super.mapToJson(body);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
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
		String uri = "/user/RoundTripLayovers";
		RoundTripBody body = new RoundTripBody();
		body.setAirportDepId(1);
		body.setAirportArrId(2);
		body.setFlightDepBeginDate(LocalDateTime.of(2021,4,1,0,0,0));
		body.setFlightDepEndDate(LocalDateTime.of(2021,4,14,0,0,0));
		body.setFlightRetBeginDate(LocalDateTime.of(2021,4,15,0,0,0));
		body.setFlightRetEndDate(LocalDateTime.of(2021,4,25,0,0,0));
		String inputJson = super.mapToJson(body);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Flight[][][] flights = super.mapFromJson(content, Flight[][][].class);
		assertTrue(flights.length >= 0);
	}
}
