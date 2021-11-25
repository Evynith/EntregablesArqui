package entregable.Entregable4.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import entregable.Entregable4.entidades.Cliente;
import entregable.Entregable4.repositorios.RepositorioCliente;

@Service
public class ClienteServicio {

	@Autowired
	private RepositorioCliente cliente;
	
	
	public List<Cliente> getClientes() {
		return this.cliente.findAll();
	}

	@Transactional
	public boolean addCliente(Cliente c) {
		this.cliente.save(c);
		return true;
	}

	public Optional<Cliente> getCliente(int idCliente) {
		return this.cliente.findById(idCliente);
	}

	@Transactional
	public boolean putCliente(Cliente c) {
		this.cliente.flush();
		return true;
	}
	
	@Transactional
	public void deleteCliente(int idCliente) {
		this.cliente.deleteById(idCliente);
	}

}
