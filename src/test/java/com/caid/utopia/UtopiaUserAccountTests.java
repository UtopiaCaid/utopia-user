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
import com.caid.utopia.entity.Account;
import com.caid.utopia.entity.AccountRole;
import com.caid.utopia.entity.Airport;
import com.caid.utopia.entity.Traveler;


public class UtopiaUserAccountTests extends UtopiaUserApplicationTests {
	
	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}
	
	@Test
	@Transactional
	void CreateAccountTest() throws Exception {
		String uri = "/user/Account";
		Account account = new Account();
		AccountRole role = new AccountRole();
		role.setRoleId(1);
		account.setRole(role);
		account.setUsername("Test username");
		account.setPassword("93418414");
		account.setEmail("jfwie@test.com");
		String inputJson = super.mapToJson(account);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
	
	@Test
	void ReadAccountTest() throws Exception {
		String uri = "/user/Account";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Account[] account = super.mapFromJson(content, Account[].class);
		assertTrue(account.length >= 0);
	}
	
	@Test
	@Transactional
	void UpdateAccountTest() throws Exception {
		String uri = "/user/Account";
		Account account = new Account();
		account.setAccountNumber(1);
		account.setEmail("test3213123@gmail.com");
		String inputJson = super.mapToJson(account);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(202, status);
	}
	
	@Test
	@Transactional
	void DeactivateAccountTest() throws Exception {
		String uri = "/user/Account";
		Account account = new Account();
		account.setAccountNumber(1);
		String inputJson = super.mapToJson(account);
		uri = "/user/Account/Deactivation";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(202,status);
	}
	
	/* role type tests */
	@Test
	void ReadAccountRolesTest() throws Exception {
		String uri = "/user/AccountRole";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		AccountRole[] roles = super.mapFromJson(content, AccountRole[].class);
		assertTrue(roles.length >= 0);
	}
	
	@Test
	void ReadAccountRoleByIdTest() throws Exception {
		String uri = "/user/AccountRole/{id}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri, 1).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertTrue(status == 200 || status == 204);
		String content = mvcResult.getResponse().getContentAsString();
		AccountRole roles = super.mapFromJson(content, AccountRole.class);
		assertTrue(roles != null);
	}
}