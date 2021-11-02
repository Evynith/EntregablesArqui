package entregable.Entregable4.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import entregable.Entregable4.entidades.Ticket;

public interface RepositorioTicket extends JpaRepository<Ticket, Integer> {
	
}
