package entregable.Entregable4.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import entregable.Entregable4.entidades.Ticket;
import entregable.Entregable4.repositorios.RepositorioCliente;
import entregable.Entregable4.repositorios.RepositorioTicket;

@Service
public class TicketServicio {

	@Autowired
	private RepositorioTicket ticket;
	@Autowired
	private RepositorioCliente cliente;
	
	@Transactional
	public void addTicket(Ticket t) {
		this.ticket.save(t);
	}
	
	public List<Ticket> getTickets() {
		return this.ticket.findAll();
	}
	
	public Optional<Ticket> getTicketById(int id){
		return this.ticket.findById(id);
	}
	
//	public List<Ticket> getTicketsByCliente(int id){
//		return this.cliente.getTicketsByCliente(id);
//	}
}
