package com.springboot.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.model.Account;
import com.springboot.model.Client;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "prod")
public class AccountServiceTest {

	@Autowired
	ClientService clientService;
	@Autowired
	AccountService accountService;
	
	List<Account> accounts;
	int initAccountNo;
	String cnp;
	
	@Before
	public void setUp() throws Exception {
		cnp = clientService.findByName("Pablo Picasso").getCNP();
		accounts = accountService.findClientAccounts(cnp);
		initAccountNo = accounts.size();
	}

	@After
	public void tearDown() throws Exception {
		int finalAccountNo = accounts.size();
		int diff = finalAccountNo - initAccountNo;
		for(int i=0; i<diff; i++){
			accountService.deleteAccount(finalAccountNo);
			finalAccountNo = finalAccountNo - 1;
		}
	}

	
	@Test
	public void findAccountByIdTest(){
		Integer id = accounts.get(0).getAccountId();
		Account account = accountService.findAccountById(id);
		assertNotNull(account);
	}
	
	@Test
	public void depositTest(){
		Account account = accounts.get(0);
		int id = account.getAccountId();
		int initSum = account.getSumMoney();
		System.out.println("Initial: id-"+id+" sum-"+initSum);
		accountService.depositInAccount(id, 300);
		accounts = accountService.findClientAccounts(clientService.findByName("Pablo Picasso").getCNP());
		Account accountUpdated = accounts.get(0);
		int finalSum = accountUpdated.getSumMoney();
		System.out.println("Final: id-"+id+" sum-"+finalSum);
		assert(finalSum == initSum+300);
	}
	
	@Test
	public void withdrawTest(){
		Account account = accounts.get(0);
		Integer id = account.getAccountId();
		int initSum = account.getSumMoney();
		accountService.withdrawFromAccount(id, 200);
		accounts = accountService.findClientAccounts(clientService.findByName("Pablo Picasso").getCNP());
		Account accountUpdated = accounts.get(0);
		int finalSum = accountUpdated.getSumMoney();
		assert(finalSum == initSum-200);
	}
	
	@Test
	public void addAccountTest(){
		int accountNo = accounts.size();
		accountService.addAccount(new Account(accountNo+1, cnp , "saving", 400, new Date()));
		Account account = accountService.findAccountById(accountNo+1);
		assertNotNull(account);
	}

	@Test
	public void findAllAccountsTest() {
		accounts = accountService.findClientAccounts(cnp);
		assertNotNull(accounts);
		for(Account a : accounts){
			System.out.println(a.getAccountId()+"\t"+a.getType()+"\t"+a.getSumMoney()+"\t"+a.getCNP());
		}
	}
	
	@Test
	public void existsAccountTest(){
		Account account = accounts.get(0);
		assert(accountService.existsAccount(account));
	}
	
	
	@Test
	public void deleteAccountTest(){
		int accountNo = accounts.size();
		accountService.addAccount(new Account(accountNo+2, cnp, "spending", 500, new Date()));
		accounts = accountService.findClientAccounts(cnp);
		accountService.deleteAccount(accountNo+2);
		accounts = accountService.findClientAccounts(cnp);
		assertNull(accountService.findAccountById(accountNo+2));
	}
}
