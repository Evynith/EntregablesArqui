package entregable.Entregable4.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entregable.Entregable4.entidades.Cliente;
import entregable.Entregable4.entidades.Ticket;

public interface RepositorioCliente extends JpaRepository<Cliente, Integer> {

//	@Query("SELECT c.tickets FROM Clientes c WHERE c.id=:id")
//	List<Ticket> getTicketsByCliente(int id);


}
