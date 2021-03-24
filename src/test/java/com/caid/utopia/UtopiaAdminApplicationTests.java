package com.caid.utopia;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UtopiaAdminApplication.class)
@WebAppConfiguration
class UtopiaAdminApplicationTests {

	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Test
	void contextLoads() {
	}

}
