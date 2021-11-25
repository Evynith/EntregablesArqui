package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
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
	private List<Client> clientList = new ArrayList<Client>();
	
	@BeforeEach
	public void beforeTest() {
		Client fede = new Client("Federico", "de Muguruza");
		Client evy = new Client("Evelyn", "Vega");
		
		clientList.add(fede);
		clientList.add(evy);
	}

	@Test
	public void newClient() {
		Client fede = this.clientList.get(0);
				
		this.repository.save(fede);
		
		Optional<Client> client = this.repository.findById(fede.getId());
		if (client.isPresent()) 
			assertTrue(client.get().getId() == fede.getId());	
	}
	
	@Test
	public void getClient() {
		Client fede = this.clientList.get(0);
		
		this.repository.save(fede);
		
		Optional<Client> client = this.repository.findById(fede.getId());
		if (client.isPresent())
			assertTrue(client.get() != null);
	}
	
	@Test
	public void deleteClientById() {
		Client fede = this.clientList.get(0);
		
		this.repository.save(fede);
		
		Optional<Client> client = this.repository.findById(fede.getId());
		if (client.isPresent()) {
			this.repository.deleteById(client.get().getId());
			assertFalse(this.repository.existsById(client.get().getId()));
		}
	}
	
	@Test
	public void modifyClient() {
		ClientController pc = new ClientController(this.repository);
		Client fede = this.clientList.get(0);
		Client evy = this.clientList.get(1);
		
		this.repository.save(fede);
		
		pc.modifyClient(fede.getId(), evy);
		
		assertTrue(fede.getName().equals(evy.getName()));
	}
}
