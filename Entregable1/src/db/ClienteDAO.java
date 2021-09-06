package db;

import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Cliente;

public interface ClienteDAO {

//	public void createTables() throws SQLException;
	
	//devuelve un listado de clientes de la db
	public ArrayList<Cliente> list() throws SQLException;
	
	//inserta en la db un cliente con los datos dados
	public void insert(Cliente p) throws SQLException;
}
