package entregable.Entregable4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entregable.Entregable4.entidades.Cliente;
import entregable.Entregable4.servicios.ClienteServicio;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteServicio clienteServicio;
	
	@GetMapping("")
	public List<Cliente> getAll() {
		return this.clienteServicio.getClientes();
	}
	
	@PostMapping("")
	public ResponseEntity<Cliente> addCliennte(@RequestBody Cliente c) {
		boolean ok = this.clienteServicio.addCliente(c);
		if (!ok) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<Cliente>(c, HttpStatus.OK);
	}
}
