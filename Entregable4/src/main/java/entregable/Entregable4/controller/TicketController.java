package entregable.Entregable4.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import entregable.Entregable4.servicios.ClienteServicio;
import entregable.Entregable4.servicios.ProductoServicio;
import entregable.Entregable4.servicios.TicketProductoServicio;
import entregable.Entregable4.servicios.TicketServicio;
import net.bytebuddy.asm.Advice.This;
import entregable.Entregable4.entidades.Cliente;
import entregable.Entregable4.entidades.Producto;
import entregable.Entregable4.entidades.Ticket;
import entregable.Entregable4.entidades.TicketProducto;
import entregable.Entregable4.pojo.DayReport;

//@RestController
@Controller
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
	@Autowired
	private TIcketProductoController ticketProductoController;

//	@GetMapping("")
//	public String saludo() {
//		return "hola";
//	}
	
	@GetMapping("")
	public String getAll(Model model) {
//		return this.ticketServicio.getTickets();
		model.addAttribute("listaTickets", this.ticketServicio.getTickets());
		return "ticket";
	}
	
	@GetMapping("/reporte")
	public List<DayReport> getReporte(){
		return this.ticketProductoServicio.getReporteVentas();
	}
	
//	@PostMapping("")
//	public ResponseStatusException addTicket(@RequestBody Ticket t) {
//		Optional<Cliente> cli = this.clienteServicio.getCliente(t.getIdCliente());
//		if (cli.isPresent()) {
//			t.setCliente( cli.get());
//		}
//		t.getProductos().forEach(p -> {
//			Optional<Producto> prod= this.productoServicio.getProducto(p.getIdProduct());
//			if (prod.isPresent()) {
//				//verifico el stock
//				if (prod.get().getCantidad() <= p.getCantidadProducto()) {
//					int cantidadRestante = this.ticketProductoServicio.getCantidadRestante(cli.get().getId(),prod.get().getID(), 3, t.getFechaEmision());
//					if (cantidadRestante > 0 && p.getCantidadProducto() <= cantidadRestante) {
//						p.setProducto(prod.get());
//						//actualliza stock porque no carga el elemento tp
//						this.productoServicio.actualizaStockEgreso(prod.get(), p.getCantidadProducto());
//						p.setTicket(t);					
//					}else {
//						//supera cantidad de producto diario 
//					}					
//				} else {
//					//no hay stock
//				}
//			}	
//		});
//		boolean ok = this.ticketServicio.addTicket(t);
//		if (ok) {
////			return new ResponseEntity<Ticket>(t, HttpStatus.OK);
//			return new ResponseStatusException(HttpStatus.OK, "Se ha realizado la acción con éxito");
//		}
//		return new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "No se ha podido completar la acción");
////		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//	}
	
	@PostMapping("")
	public ResponseEntity<Ticket> addTicket(@RequestBody Ticket t) {
		Optional<Cliente> cli = this.clienteServicio.getCliente(t.getIdCliente());
		if (cli.isPresent()) {
			t.setCliente( cli.get());
		}
		List<TicketProducto> tp = t.getProductos();
		t.setProductos(new ArrayList<TicketProducto>());
		boolean ok = this.ticketServicio.addTicket(t);	
		if (ok && !tp.isEmpty()) {
		tp.forEach(p -> {
			try {
				this.ticketProductoController.addProducto(t.getID(), p).getBody();				
			} catch (Exception e) {
				// TODO: handle exception, no puede meter ese producto en el ticket
			}		
		});	
//			return new ResponseStatusException(HttpStatus.OK, "Se ha realizado la acción con éxito");
		return new ResponseEntity<Ticket>(t,HttpStatus.OK);
		}
		this.deleteTicket(t.getID());
//		return new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "No se ha podido completar la acción");
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Ticket> putTicket(@PathVariable("id")int id, @RequestBody Ticket t) {
		Optional<Ticket> to = this.ticketServicio.getTicketById(id);
		if (to.isPresent()) {
			//la fecha no tiene sentido poder editarse¿?
//			if (t.getCliente() != null) to.get().setCliente(t.getCliente());
			if (t.getIdCliente() != 0) {
			Optional<Cliente> cli = this.clienteServicio.getCliente(t.getIdCliente());
			if (cli.isPresent()) {
				to.get().setCliente( cli.get());
			}}
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
