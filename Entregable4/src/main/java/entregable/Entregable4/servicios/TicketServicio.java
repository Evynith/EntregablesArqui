package entregable.Entregable4.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import entregable.Entregable4.entidades.Ticket;
import entregable.Entregable4.repositorios.RepositorioTicket;

@Service
public class TicketServicio {

	@Autowired
	private RepositorioTicket ticket;

	@Transactional
	public boolean addTicket(Ticket t) {
		this.ticket.save(t);
		return true;
	}
	
	public List<Ticket> getTickets() {
		return this.ticket.findAll();		
	}
	
	public Optional<Ticket> getTicketById(int id){
		return this.ticket.findById(id);
	}

	public boolean putTicket(Ticket ticket2) {
		this.ticket.flush();
		return true;
	}

	public void deleteTicket(int id) {
		this.ticket.deleteById(id);
	}

	
}
