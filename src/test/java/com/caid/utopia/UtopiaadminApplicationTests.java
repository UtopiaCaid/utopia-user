package com.caid.utopia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.caid.utopia.controller.FlightsController;
import com.caid.utopia.entity.Flights;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UtopiaadminApplication.class)
@WebAppConfiguration
class UtopiaadminApplicationTests {
	
//	private MockMvc mockMvc;
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
//	@Before
//	private void setUp() {
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//	}
	
	@Test
	void contextLoads() {
	}
	
	//View Flight Tests
	@Autowired
	FlightsController flightsController;
	
	@MockBean
	private com.caid.utopia.repo.FlightsRepo FlightsRepo;
	
	@Test
	public void flightsTest() {
		Flights flightOne = new Flights();
		Flights flightTwo = new Flights();
		
		flightOne.setFlightNo(223);
		flightOne.setStatus("Cancelled");
		flightTwo.setFlightNo(323);
		flightTwo.setStatus("On Schedule");
		
		when(FlightsRepo.findAll()).thenReturn(Stream
				.of(flightOne, flightTwo)
				.collect(Collectors.toList()));
		assertEquals(2, flightsController.getAllFlights().getBody().size());
	}
	
	@Test
	public void flightsIdTest() {
		Flights flightOne = new Flights();
		Flights flightTwo = new Flights();
		
		flightOne.setFlightNo(223);
		flightOne.setStatus("Cancelled");
		flightTwo.setFlightNo(1);
		flightTwo.setStatus("On Schedule");
		
	}

}
