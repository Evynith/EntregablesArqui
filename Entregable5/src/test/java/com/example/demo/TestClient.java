package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import controllers.ClientController;
import models.Client;
import repositories.ClientRepository;

@DataJpaTest
public class TestClient {
	
	@Autowired
	private ClientRepository repository;

	@Test
	public void newClient() {
		Client newClient = new Client("Federico", "de Muguruza");
		
		this.repository.save(newClient);
		
		Optional<Client> client = this.repository.findById(newClient.getId());
		if (client.isPresent()) 
			assertTrue(client.get().getId() == newClient.getId());	
	}
	
	@Test
	public void getClient() {
		Client newClient = new Client("Federico", "de Muguruza");
		
		this.repository.save(newClient);
		
		Optional<Client> c = this.repository.findById(newClient.getId());
		if (c.isPresent())
			assertTrue(c.get() != null);
	}
	
	@Test
	public void deleteAllClients() {
		Client newClient = new Client("Federico", "de Muguruza");
		Client newClient2 = new Client("Federico2", "de Muguruza2");
		
		this.repository.save(newClient);
		this.repository.save(newClient2);
		
		this.repository.deleteAll();
		
		assertThat(repository.findAll()).isEmpty();
	}
	
	@Test
	public void deleteClientById() {
		Client newClient = new Client("Federico", "de Muguruza");
		
		this.repository.save(newClient);
		
		Optional<Client> c = this.repository.findById(newClient.getId());
		if (c.isPresent())
			this.repository.deleteById(c.get().getId());
	}
	
	@Test
	public void modifyClient() {
		ClientController pc = new ClientController(this.repository);
		Client newClient = new Client("Federico", "de Muguruza");
		Client newClient2 = new Client("Federico2", "de Muguruza2");
		
		this.repository.save(newClient);
		
		pc.modifyClient(newClient.getId(), newClient2);
		
		assertTrue(newClient.getName().equals(newClient2.getName()));
	}
}
