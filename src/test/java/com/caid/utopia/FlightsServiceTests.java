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

public class FlightsServiceTests extends UtopiaAdminApplicationTests {

	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}
	
	@Test
	@Transactional
	public void flightInsertionTest() throws Exception {
		String uri = "/flights";
		
		Flight flight = new Flight();
		Airport airportIdDeparture = new Airport();
		airportIdDeparture.setAirportId(1);
		airportIdDeparture.setAirportCode(10001);
		airportIdDeparture.setCity("Afognak Lake");
		airportIdDeparture.setAirportName("01A");
		airportIdDeparture.setStatus("active");
		Airport airportIdArrival = new Airport();
		airportIdArrival.setAirportId(2);
		airportIdArrival.setAirportCode(10056);
		airportIdArrival.setCity("Kodiak Island");
		airportIdArrival.setAirportName("A43");
		airportIdArrival.setStatus("active");
		flight.setairportArrival(airportIdArrival);
		flight.setairportDeparture(airportIdDeparture);
		
		Aircraft aircraft = new Aircraft();
		aircraft.setAircraftId(1);
		aircraft.setFirstClassCount(10);
		aircraft.setSecondClassCount(0);
		aircraft.setThirdClassCount(0);
		AircraftType aircraftType = new AircraftType();
		aircraftType.setAircraftType(40);
		aircraftType.setaircraftTypeName("DEHAVILLAND DHC2");
		aircraftType.setSeatMaximum(10);
		aircraftType.setManufacturer("DEHAVILLAND OF CANADA");
		aircraft.setAircraftType(aircraftType);
		flight.setAircraft(aircraft);
		LocalDate departure = LocalDate.of(2020, 1, 1);
		flight.setDeparture(departure);
		LocalDate arrival = LocalDate.of(2020, 1, 1);
		flight.setArrival(arrival);
		flight.setStatus("completed");
		
		String requestBody = super.mapToJson(flight);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(requestBody))
				.andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
	}
	
	
	@Test
	@Transactional
	public void updateFlightTest() throws Exception {
			String uri = "/flights";
			
			Flight flight = new Flight();
			flight.setFlightNo(5);
			Airport airportIdDeparture = new Airport();
			airportIdDeparture.setAirportId(1);
			airportIdDeparture.setAirportCode(10001);
			airportIdDeparture.setCity("Afognak Lake");
			airportIdDeparture.setAirportName("01A");
			airportIdDeparture.setStatus("active");
			Airport airportIdArrival = new Airport();
			airportIdArrival.setAirportId(2);
			airportIdArrival.setAirportCode(10056);
			airportIdArrival.setCity("Kodiak Island");
			airportIdArrival.setAirportName("A43");
			airportIdArrival.setStatus("active");
			flight.setairportArrival(airportIdArrival);
			flight.setairportDeparture(airportIdDeparture);
			
			Aircraft aircraft = new Aircraft();
			aircraft.setAircraftId(1);
			aircraft.setFirstClassCount(10);
			aircraft.setSecondClassCount(0);
			aircraft.setThirdClassCount(0);
			AircraftType aircraftType = new AircraftType();
			aircraftType.setAircraftType(40);
			aircraftType.setaircraftTypeName("DEHAVILLAND DHC2");
			aircraftType.setSeatMaximum(10);
			aircraftType.setManufacturer("DEHAVILLAND OF CANADA");
			aircraft.setAircraftType(aircraftType);
			flight.setAircraft(aircraft);
			LocalDate departure = LocalDate.of(2020, 1, 1);
			flight.setDeparture(departure);
			LocalDate arrival = LocalDate.of(2020, 1, 1);
			flight.setArrival(arrival);
			flight.setStatus("completed");
			
			String requestBody = super.mapToJson(flight);
			
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
		      .contentType(MediaType.APPLICATION_JSON_VALUE)
		      .content(requestBody)).andReturn();
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(200, status);
		   
		   String content = mvcResult.getResponse().getContentAsString();
		   assertEquals(true, content.contains("Afognak Lake"));
	}
	
	@Test
	@Transactional
	public void updateFlightBadArgumentTest() throws Exception {
		//airportIdDeparture (which is a required field) removed in this test case)
		String uri = "/flights";
		
		Flight flight = new Flight();
		flight.setFlightNo(5);

		Airport airportIdArrival = new Airport();
		airportIdArrival.setAirportId(2);
		airportIdArrival.setAirportCode(10056);
		airportIdArrival.setCity("Kodiak Island");
		airportIdArrival.setAirportName("A43");
		airportIdArrival.setStatus("active");
		flight.setairportArrival(airportIdArrival);
		
		Aircraft aircraft = new Aircraft();
		aircraft.setAircraftId(1);
		aircraft.setFirstClassCount(10);
		aircraft.setSecondClassCount(0);
		aircraft.setThirdClassCount(0);
		AircraftType aircraftType = new AircraftType();
		aircraftType.setAircraftType(40);
		aircraftType.setaircraftTypeName("DEHAVILLAND DHC2");
		aircraftType.setSeatMaximum(10);
		aircraftType.setManufacturer("DEHAVILLAND OF CANADA");
		aircraft.setAircraftType(aircraftType);
		flight.setAircraft(aircraft);
		LocalDate departure = LocalDate.of(2020, 1, 1);
		flight.setDeparture(departure);
		LocalDate arrival = LocalDate.of(2020, 1, 1);
		flight.setArrival(arrival);
		flight.setStatus("completed");
		
		String requestBody = super.mapToJson(flight);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE)
	      .content(requestBody)).andReturn();
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(400, status);
	}
	
	
	@Test
	@Transactional
	public void deleteFlightTest() throws Exception {
		String uri = "/flights";
		
		Flight flight = new Flight();
		flight.setFlightNo(5);

		
		String requestBody = super.mapToJson(flight);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE)
	      .content(requestBody)).andReturn();
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	}
	
	@Test
	@Transactional
	public void deleteFlightNoIdTest() throws Exception {
		String uri = "/flights";
		
		Flight flight = new Flight();

		
		String requestBody = super.mapToJson(flight);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE)
	      .content(requestBody)).andReturn();
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(417, status);
	}
	
	@Test
	@Transactional
	public void flightRetrievalTest() throws Exception {
		String uri = "/flights";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	
}
