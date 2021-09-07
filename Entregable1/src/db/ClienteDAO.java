package db;

import java.sql.SQLException;
import java.util.ArrayList;
import entidades.Cliente;
import entidades.ClientePorFacturacionDesc;

public interface ClienteDAO {
	
	/**
	 * Devuelve un listado de los clientes ordenados por mayor facturación.
	 * @return Devuelve una lista de clientes.
	 * @throws SQLException
	 */
	ArrayList<ClientePorFacturacionDesc> list() throws SQLException;
	
	/**
	 * Inserta un cliente.
	 * @param p Que es el cliente.
	 * @throws SQLException
	 */
	int insert(Cliente p) throws SQLException;
}
