package entregable.Entregable4.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import entregable.Entregable4.servicios.ClienteServicio;
import entregable.Entregable4.servicios.ProductoServicio;
import entregable.Entregable4.servicios.TicketProductoServicio;
import entregable.Entregable4.servicios.TicketServicio;
import entregable.Entregable4.entidades.Cliente;
import entregable.Entregable4.entidades.Producto;
import entregable.Entregable4.entidades.Ticket;
import entregable.Entregable4.pojo.DayReport;

@RestController
@RequestMapping("/ventas")
public class TicketController {
	
	@Autowired
	private TicketServicio ticketServicio;
	@Autowired
	private ClienteServicio clienteServicio;
	@Autowired
	private ProductoServicio productoServicio;
	@Autowired
	private TicketProductoServicio ticketProductoServicio;

//	@GetMapping("")
//	public String saludo() {
//		return "hola";
//	}
	
	@GetMapping("")
	public List<Ticket> getAll() {
		return this.ticketServicio.getTickets();
	}
	
	@GetMapping("/reporte")
	public List<DayReport> getReporte(){
		return this.ticketProductoServicio.getReporteVentas();
	}
	
	@PostMapping("")
	public ResponseEntity<Ticket> addTicket(@RequestBody Ticket t) {
		Optional<Cliente> cli = this.clienteServicio.getCliente(t.getIdCliente());
		if (cli.isPresent()) {
			t.setCliente( cli.get());
		}
		t.getProductos().forEach(p -> {
			Optional<Producto> prod= this.productoServicio.getProducto(p.getIdProduct());
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
	
	@PutMapping("/{id}")
	public ResponseEntity<Ticket> putTicket(@PathVariable("id")int id, @RequestBody Ticket t) {
		Optional<Ticket> to = this.ticketServicio.getTicketById(id);
		if (to.isPresent()) {
			//la fecha no tiene sentido poder editarse¿?
			if (t.getCliente() != null) to.get().setCliente(t.getCliente());
			//editarle los productos en su xontrolador propio
			boolean ok = this.ticketServicio.putTicket(to.get());
			if (ok) {
				return new ResponseEntity<Ticket>(to.get(), HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	@DeleteMapping("/{id}")
	public void deleteTicket(@PathVariable("id")int id) {
		this.ticketServicio.deleteTicket(id);
	}
	
}
