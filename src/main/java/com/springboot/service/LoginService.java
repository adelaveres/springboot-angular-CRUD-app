package com.springboot.service;

import com.springboot.model.LoginAccount;

public interface LoginService {
	
	LoginAccount findByUsername(String username);
}
