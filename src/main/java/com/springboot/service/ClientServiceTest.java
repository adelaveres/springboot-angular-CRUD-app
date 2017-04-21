package com.springboot.service;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.model.Client;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "prod")
public class ClientServiceTest{

	@Autowired
	ClientService clientService;
	int initNoClients;
	
	@Before
	public void setUp(){
		initNoClients = clientService.findAllClients().size();
	}
	@After
	public void tearDown(){
		int finalNoClients = clientService.findAllClients().size();
		int difference = finalNoClients - initNoClients;
		for(int i=0; i<difference; i++){
			clientService.deleteClientById(finalNoClients);
			finalNoClients= finalNoClients-1;
		}
	}
	
	@Test
	public void findByIdTest() {
		Client client = clientService.findById(4);
		assertNotNull(client);
	}
	
	@Test
	public void findByNameTest(){
		assertNotNull(clientService.findByName("Pablo Picasso"));
	}

	@Test
	public void addClientTest(){
		int noClients = clientService.findAllClients().size();
		clientService.addClient(new Client(noClients+1,"2018201820189",123123,"Claudia Grigore","str. Felinarelor, nr.5"));
		System.out.println("After addClientTest: \n");
		System.out.println(clientService.findById(noClients+1));
		
		List<Client> clients = clientService.findAllClients();
		for(Client c : clients)	
			System.out.println(c.getName());
			
		assertNotNull(clientService.findById(noClients+1));
	}
	
	@Test
	public void updateClientTest(){
		int noClients = clientService.findAllClients().size();
		clientService.addClient(new Client(noClients+2, "2014201420142",113111,"Marcel Crisan","str. Pomilor, nr.9"));
		Client client = clientService.findById(noClients+2);
		
		List<Client> clients = clientService.findAllClients();
		for(Client c : clients)	
			System.out.println(c.getName());
		
		client.setName("Cristina Crisan");
		Integer id = client.getId();
		clientService.updateClient(client);
		
		List<Client> clients2 = clientService.findAllClients();
		for(Client c : clients2)	
			System.out.println(c.getName());
		
		assert(clientService.findByName("Cristina Crisan").getId() == id);
	}
	
	@Test 
	public void findAllClientsTest(){
		List<Client> clients = clientService.findAllClients();
		for(Client c : clients){
			System.out.println(c.getId()+"\t"+c.getName()+"\t"+c.getCNP()+"\t"+c.getAddress());
		}
		assertNotNull(clients.size());
	}
	
	@Test
	public void deleteClientTest(){
		int noClients = clientService.findAllClients().size();
		clientService.addClient(new Client(noClients+1, "2017201720147",113111,"Marcel Crisan","str. Pomilor, nr.9"));
		Client client = clientService.findByName("Marcel Crisan");
		
		clientService.deleteClientById(client.getId());
		assertNull(clientService.findById(client.getId()));
	}
	
	@Test
	public void existsClientTest(){
		assertNotNull(clientService.findByName("Pablo Picasso"));
	}
	

}
