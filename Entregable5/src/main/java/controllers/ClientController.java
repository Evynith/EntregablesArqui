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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import models.Client;
import repositories.ClientRepository;

@RestController
@RequestMapping("/clients")
@Api(value = "ClientController", description = "API REST para la entidad cliente")
public class ClientController {

	@Qualifier("clientRepository")
	@Autowired
	private final ClientRepository repository;
	
	public ClientController(@Qualifier("clientRepository") ClientRepository repository) {
		 this.repository = repository;
	}
	
    @ApiOperation(value = "Crea una cliente", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 409, message = "Already exists") })
	@PostMapping("")
	public Client newClient(@RequestBody Client client) {
		return this.repository.save(client);
	}
	
    @ApiOperation(value = "Retorna todos los clientes", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
	@GetMapping("")
	public Iterable<Client> getAll() {
		return this.repository.findAll();
	}
	
    @ApiOperation(value = "Retorna un cliente por el id", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Invalid id supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
	@GetMapping("/{id}")
	public Optional<Client> getClient(@PathVariable Long id) {
		return this.repository.findById(id);
	}
	
    @ApiOperation(value = "Elimina un cliente por el id", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Invalid id supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
	@DeleteMapping("/{id}")
	public void deleteClient(@PathVariable Long id) {
		this.repository.deleteById(id);
	}
	
    @ApiOperation(value = "Edita un cliente por el id", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Invalid id supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
	@PutMapping("/{id}")
	public void modifyClient(@PathVariable Long id, @RequestBody Client client) {
		Optional<Client> c = getClient(id);
		if (c.isPresent()) {
			c.get().setName(client.getName());
			c.get().setSurname(client.getSurname());
			this.repository.save(c.get());
		}
	}
}
