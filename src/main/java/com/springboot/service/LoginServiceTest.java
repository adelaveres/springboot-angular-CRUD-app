package com.springboot.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "prod")
public class LoginServiceTest {

	@Autowired
	LoginService loginService;
	
	@Test
	public void findByUsernameTest(){
		assertNotNull(loginService.findByUsername("employee"));
	}
	
}
