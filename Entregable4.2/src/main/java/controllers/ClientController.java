package controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.Client;
import repositories.ClientRepository;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@Qualifier("clientRepository")
	@Autowired
	private final ClientRepository repository;
	
	public ClientController(@Qualifier("clientRepository") ClientRepository repository) {
		 this.repository = repository;
	}
	
	@PostMapping("")
	public Client newClient(@RequestBody Client client) {
		return this.repository.save(client);
	}
	
	@GetMapping("")
	public Iterable<Client> getAll() {
		return this.repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Client> getClient(@PathVariable Long id) {
		return this.repository.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteClient(@PathVariable Long id) {
		this.repository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public void modifyClient(@PathVariable Long id, @RequestBody Client client) {
		this.repository.deleteById(id);
		this.repository.save(client);
	}
}
