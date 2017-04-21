package com.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.model.LoginAccount;
import com.springboot.repositories.LoginAccountRepository;

@Service("loginService")
@Transactional
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	LoginAccountRepository loginAccountRepository;
	
	public LoginAccount findByUsername(String username){
		return loginAccountRepository.findByUsername(username);
	}

}
