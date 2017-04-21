package com.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.springboot.model.Account;
import com.springboot.model.Client;
import com.springboot.model.Employee;
import com.springboot.model.LoginAccount;
import com.springboot.service.AccountService;
import com.springboot.service.ClientService;
import com.springboot.service.EmployeeService;
import com.springboot.service.LoginService;
import com.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	ClientService clientService; //Service which will do all client data retrieval/manipulation work
	@Autowired
	AccountService accountService;
	@Autowired
	LoginService loginService;
	@Autowired
	EmployeeService employeeService;

	

	// -------------------Retrieve All Clients---------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<Client>> listAllClients() {
		
		List<Client> clients = clientService.findAllClients();
		
		if (clients.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You may decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
	}
	
	// -------------------Retrieve All Employees---------------------------------------------

		@RequestMapping(value = "/employee/", method = RequestMethod.GET)
		public ResponseEntity<List<Employee>> listAllEmployees() {
			
			List<Employee> employees = employeeService.findAllEmployees();
			
			if (employees.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
		}
	
	// -------------------Retrieve Single Client------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getClient(@PathVariable("id") Integer id) {
		 
		logger.info("Fetching User with id {}", id);
		Client client = clientService.findById(id);
		if (client == null) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("User with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}

	// ------------------- Get Client Accounts -------------------------------------------
	
	@RequestMapping(value = "/user/{id}/accounts", method = RequestMethod.GET)
	public ResponseEntity<?> getClientAccounts(@PathVariable("id") Integer id){
		
		logger.info("Fetching Accounts for User with id {}",id);
		Client client = clientService.findById(id);
		
		if (client == null) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("User with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		List<Account> accounts = accountService.findClientAccounts(client.getCNP());
		
		return new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
	}
	
	// -------------------Get Single Employee------------------------------------------

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getEmployee(@PathVariable("id") Integer id) {
		 
		logger.info("Fetching Employee with id {}", id);
		Employee employee = employeeService.findById(id);
		if (employee == null) {
			logger.error("Employee with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Employee with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	// ------------------- Get Single LoginAccount------------------------------------------

		@RequestMapping(value = "/login/{username}", method = RequestMethod.GET)
		public ResponseEntity<?> getLoginAccount(@PathVariable("username") String username) {
			 
			logger.info("Fetching Login Account for user {}", username);
			LoginAccount loginAccount = loginService.findByUsername(username);
			if (loginAccount == null) {
				logger.error("Login Account for user {} not found.", username);
				return new ResponseEntity(new CustomErrorType("Login Account for user " + username 
						+ " not found"), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<LoginAccount>(loginAccount, HttpStatus.OK);  
		}
	
		
		
	// ---------------------------- Login -------------------------------------------
	@RequestMapping(value = "/login/{pass}", method = RequestMethod.PUT)
	public ResponseEntity<?> login(@PathVariable("pass") String enteredPassword, @RequestBody LoginAccount loginAccount){
		logger.info("Logging in user: {}", loginAccount.getUsername());
		
		String username = loginAccount.getUsername();
		String realPassword = loginAccount.getPassword(); 
		
		if(!enteredPassword.equals(realPassword)){
			logger.error("Incorrect Password for user {}", username);
			return new ResponseEntity(new CustomErrorType("Incorrect Password for user " + username ),
					HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}
	
	// -------------------Create an Employee -------------------------------------------

	@RequestMapping(value = "/employee/", method = RequestMethod.POST)
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", employee);

		if (employeeService.existsEmployee(employee)) {
			logger.error("Unable to create. An Employee with name {} already exist", employee.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. An Employee with name " + 
					employee.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		employeeService.addEmployee(employee);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/employee/{id}").buildAndExpand(employee.getCNP()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	// -------------------Create a Client -------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<?> createClient(@RequestBody Client client, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", client);

		if (clientService.existsClient(client)) {
			logger.error("Unable to create. A Client with name {} already exist", client.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A Client with name " + 
			client.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		clientService.addClient(client);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(client.getCNP()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Create an Account -----------------------------------------------
	
	@RequestMapping(value = "/user/account", method = RequestMethod.POST)
	public ResponseEntity<?> createAccount(@RequestBody Account account, UriComponentsBuilder ucBuilder){
		logger.info("Creating Account: {}", account);
		
		if(accountService.existsAccount(account)){
			logger.error("Unable to create. An account with id {} already exists.", account.getAccountId());
			return new ResponseEntity(new CustomErrorType("Unable to create. An account with id "+account.getAccountId()+
					" already exists."),HttpStatus.CONFLICT);
		}
		accountService.addAccount(account);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/account/{accountId}").buildAndExpand(account.getCNP()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	
	//------------------- Deposit in Client Account -------------------------------------
	
	@RequestMapping(value = "/user/account/{accountId}/deposit", method = RequestMethod.PUT)
	public ResponseEntity<?> depositInAccount(@PathVariable("accountId") Integer accountId, @RequestBody int sum ){
		logger.info("Depositing in account {}", accountId);
		
		//DEPOSIT
		accountService.depositInAccount(accountId, sum);
		//
		Account account = accountService.findAccountById(accountId);
		
		if (account == null) {
			logger.error("Unable to deposit. Account with id {} not found.", accountId);
			return new ResponseEntity(new CustomErrorType("Unable to deposit. Account with id " + accountId + " not found."),
					HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Account>(HttpStatus.OK);
	}
	
	// ------------------- Withdraw from a Client Account ---------------------------------
	@RequestMapping(value = "/user/account/{accountId}/withdraw", method = RequestMethod.PUT)
	public ResponseEntity<?> withdrawFromAccount(@PathVariable("accountId") Integer accountId, @RequestBody int sum ){
		logger.info("Withdrawing from account {}", accountId);
		
		//WITHDRAW
		accountService.withdrawFromAccount(accountId, sum);
		//
		Account account = accountService.findAccountById(accountId);
		
		if (account == null) {
			logger.error("Unable to withdraw. Account with id {} not found.", accountId);
			return new ResponseEntity(new CustomErrorType("Unable to withdraw. Account with id " + accountId + " not found."),
					HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Account>(HttpStatus.OK);
	}
	
	
	// ------------------- Update a Client ------------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateClient(@PathVariable("id") Integer id, @RequestBody Client client) {
		logger.info("Updating User with id {}", id);

		Client currentClient = clientService.findById(id);

		if (currentClient == null) {
			logger.error("Unable to update. Client with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Client with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		
		currentClient.setName(client.getName());
		currentClient.setCNP(client.getCNP());
		currentClient.setIDNumber(client.getIDNumber());
		currentClient.setAddress(client.getAddress());

		clientService.updateClient(currentClient);
		return new ResponseEntity<Client>(currentClient, HttpStatus.OK);
	}
	
	// ------------------- Update an Employee ------------------------------------------------

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee) {
		logger.info("Updating Employee with id {}", id);

		Employee currentEmployee = employeeService.findById(id);
		System.out.println("Updating Employee with employee: "+employee.getName()+", CNP: "+employee.getCNP()+", address:" +employee.getAddress());
		
		if (currentEmployee == null) {
			logger.error("Unable to update. Employee with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Employee with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		
		String newName = employee.getName();
		String newCNP = employee.getCNP();
		String newAddress = employee.getAddress();

		employeeService.updateEmployee(id, newName, newCNP, newAddress);
		currentEmployee = employeeService.findById(id);
		
		return new ResponseEntity<Employee>(currentEmployee, HttpStatus.OK);
	}
	
	// ------------------- Delete Client Account-----------------------------------
	@RequestMapping(value = "user/account/{accountId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAccount(@PathVariable("accountId") int accountId){
		logger.info("Deleting Account no {}", accountId);
		
		Account account = accountService.findAccountById(accountId);
		if (account == null) {
			logger.error("Unable to delete. Account with id {} not found.", accountId);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Account with id " + accountId + " not found."),
					HttpStatus.NOT_FOUND);
		}
		
		
		accountService.deleteAccount(accountId);
		return new ResponseEntity<Account>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete a Client-----------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteClient(@PathVariable("id") Integer id) {
		logger.info("Fetching & Deleting Client with id {}", id);

		Client client = clientService.findById(id);
		if (client == null) {
			logger.error("Unable to delete. Client with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Client with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		clientService.deleteClientById(id);
		return new ResponseEntity<Client>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete an Employee-----------------------------------------

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") Integer id) {
		logger.info("Fetching & Deleting Employee with id {}", id);

		Employee employee = employeeService.findById(id);
		if (employee == null) {
			logger.error("Unable to delete. Employee with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Employee with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		employeeService.deleteEmployeeById(id);
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}
	
	// ------------------- Delete All Clients-----------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<Client> deleteAllClients() {
		logger.info("Deleting All Users");

		clientService.deleteAllClients();
		return new ResponseEntity<Client>(HttpStatus.NO_CONTENT);
	}
}