package com.springboot.service;

import java.util.List;

import com.springboot.model.Account;

public interface AccountService {

	List<Account> findClientAccounts(String cnp);
	
	void depositInAccount(int accountId, int sum);
	
	void withdrawFromAccount(int accountId, int sum);
	
	Account findAccountById(int accountId);
	
	void deleteAccount(int accountId);
	
	void addAccount(Account account);
	
	boolean existsAccount(Account account);

}
