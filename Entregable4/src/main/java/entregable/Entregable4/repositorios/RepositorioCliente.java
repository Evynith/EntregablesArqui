package entregable.Entregable4.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import entregable.Entregable4.entidades.Cliente;

public interface RepositorioCliente extends JpaRepository<Cliente, Integer> {

}
