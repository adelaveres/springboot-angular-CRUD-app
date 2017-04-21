package com.springboot.service;

import java.util.List;

import com.springboot.model.Client;
import com.springboot.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("clientService")
@Transactional
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientRepository clientRepository;


	public Client findById(Integer id) {
		return clientRepository.findById(id);
	}

	public Client findByName(String name) {
		return clientRepository.findByName(name);
	}

	public void addClient(Client client) {
		clientRepository.save(client);
	}

	public void updateClient(Client client){
		addClient(client);
	}

	public void deleteClientById(Integer id){
		clientRepository.delete(id);
	}

	public void deleteAllClients(){
		clientRepository.deleteAll();
	}

	public List<Client> findAllClients(){
		return clientRepository.findAll();
	}

	public boolean existsClient(Client client) {
		return findByName(client.getName()) != null;
	}

}