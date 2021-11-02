package entregable.Entregable4.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entregable.Entregable4.servicios.ClienteServicio;
import entregable.Entregable4.servicios.ProductoServicio;
import entregable.Entregable4.servicios.TicketServicio;
import entregable.Entregable4.entidades.Cliente;
import entregable.Entregable4.entidades.Producto;
import entregable.Entregable4.entidades.Ticket;

@RestController
@RequestMapping("/ventas")
public class TicketController {
	
	@Autowired
	private TicketServicio ticketServicio;
	@Autowired
	private ClienteServicio clienteServicio;
	@Autowired
	private ProductoServicio productoServicio;

//	@GetMapping("")
//	public String saludo() {
//		return "hola";
//	}
	
	@GetMapping("")
	public List<Ticket> getAll() {
		return this.ticketServicio.getTickets();
	}
	
	@PostMapping("")
	public ResponseEntity<Ticket> addTicket(@RequestBody Ticket t) {
		Optional<Cliente> cli = this.clienteServicio.getCliente(t.getIdCliente());
		if (cli.isPresent()) {
			t.setCliente( cli.get());
		}
		t.getProductos().forEach(p -> {
			Optional<Producto> prod= this.productoServicio.getProducto(p.getIdProducto());
			if (prod.isPresent()) {
				p.setProducto(prod.get());
				p.setTicket(t);
			}	
		});
		boolean ok = this.ticketServicio.addTicket(t);
		if (!ok) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<Ticket>(t, HttpStatus.OK);
	}
	
	
}
