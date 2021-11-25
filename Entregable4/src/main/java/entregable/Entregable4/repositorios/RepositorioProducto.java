package entregable.Entregable4.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import entregable.Entregable4.entidades.Producto;

public interface RepositorioProducto extends JpaRepository<Producto, Integer> {
	
}
