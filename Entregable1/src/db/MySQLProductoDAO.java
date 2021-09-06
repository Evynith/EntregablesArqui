package db;

import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Factura;
import entidades.Producto;

public class MySQLProductoDAO implements ProductoDAO {
	
	public MySQLProductoDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Producto> list(Factura f) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Producto p) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Producto getMayorRecaudacion() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
