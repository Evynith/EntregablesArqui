package entregable.Entregable4.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import entregable.Entregable4.entidades.TicketProducto;
import entregable.Entregable4.repositorios.RepositorioProducto;
import entregable.Entregable4.repositorios.RepositorioTicketProducto;

@Service
public class TicketProductoServicio {

	@Autowired
	private RepositorioTicketProducto ticketProducto;
	
	public boolean add(TicketProducto tp) {
		return this.ticketProducto.save(tp) != null;
	}

	public void delete(int idElement) {
		this.ticketProducto.deleteById(idElement);
	}

	public Optional<TicketProducto> getById(int idElement) {
		return this.ticketProducto.findById(idElement);
	}

	public boolean put(TicketProducto ticketProducto2) {
		this.ticketProducto.flush();
		return true;
	}
	

}
