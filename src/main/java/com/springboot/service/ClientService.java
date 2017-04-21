package com.springboot.service;

import com.springboot.model.Client;
import java.util.List;

public interface ClientService {
	
	Client findById(Integer id);

	Client findByName(String name);

	void addClient(Client client);

	void updateClient(Client client);

	void deleteClientById(Integer id);

	void deleteAllClients();

	List<Client> findAllClients();

	boolean existsClient(Client client);
	
}