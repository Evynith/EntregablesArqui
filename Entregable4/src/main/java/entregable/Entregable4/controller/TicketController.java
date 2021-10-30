package entregable.Entregable4.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entregable.Entregable4.servicios.TicketServicio;
import entregable.Entregable4.entidades.Ticket;

@RestController
@RequestMapping("/ventas")
public class TicketController {
	
	private TicketServicio ticketServicio;

//	@GetMapping("")
//	public String saludo() {
//		return "hola";
//	}
	
	@GetMapping("")
	public List<Ticket> getAll() {
		return this.ticketServicio.getTickets();
	}
	
	
	
}
