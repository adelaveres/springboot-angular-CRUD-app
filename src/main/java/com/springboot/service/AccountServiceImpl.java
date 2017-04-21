package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.model.Account;
import com.springboot.repositories.AccountRepository;

@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountRepository accountRepository;

	public List<Account> findClientAccounts(String cnp){
		return accountRepository.findClientAccounts(cnp);
	}
	
	public void depositInAccount(int accountId, int sum){
		accountRepository.updateAccount(accountId, sum);
	}
	
	public void withdrawFromAccount(int accountId, int sum){
		accountRepository.updateAccount(accountId, -sum);
	}
	
	public Account findAccountById(int accountId){
		return accountRepository.findAccountById(accountId);
	}
	
	public void deleteAccount(int accountId){
		accountRepository.deleteAccount(accountId);
	}
	
	public void addAccount(Account account){
		accountRepository.save(account);
	}
	
	public boolean existsAccount(Account account){
		return accountRepository.findAccountById(account.getAccountId()) != null;
	}	

}
