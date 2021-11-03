package entregable.Entregable4.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entregable.Entregable4.entidades.Cliente;
import entregable.Entregable4.pojo.ClientReport;
import entregable.Entregable4.servicios.ClienteServicio;
import entregable.Entregable4.servicios.TicketProductoServicio;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteServicio clienteServicio;
	@Autowired
	private TicketProductoServicio ticketProductoServicio;
	
	@GetMapping("")
	public List<Cliente> getAll() {
		return this.clienteServicio.getClientes();
	}
	
	@GetMapping("/reporte")
	public List<ClientReport> getReporte() {
		return this.ticketProductoServicio.getReporteClientes();
	}
	
	@PostMapping("")
	public ResponseEntity<Cliente> addCliennte(@RequestBody Cliente c) {
		boolean ok = this.clienteServicio.addCliente(c);
		if (!ok) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<Cliente>(c, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> putCliente(@PathVariable("id")int id, @RequestBody Cliente c){
		Optional<Cliente> viejo = this.clienteServicio.getCliente(id);
		if(viejo.isPresent()) {
			if (c.getNombre() != null) viejo.get().setNombre(c.getNombre());
			if (c.getDni() != 0) viejo.get().setDni(c.getDni());
			//no se si cambiarle los tickets
			boolean ok = this.clienteServicio.putCliente(viejo.get());
			if (ok) {
				return new ResponseEntity<Cliente>(viejo.get(), HttpStatus.OK);
			}
			
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCliente(@PathVariable("id")int id) {
		this.clienteServicio.deleteCliente(id);
	}
}
