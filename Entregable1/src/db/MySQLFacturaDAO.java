package db;

import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Cliente;
import entidades.Factura;

public class MySQLFacturaDAO implements FacturaDAO {
	
	public MySQLFacturaDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Factura> list(Cliente c) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Factura f) throws SQLException {
		// TODO Auto-generated method stub

	}

}
