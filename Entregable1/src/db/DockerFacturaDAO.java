package db;

import java.sql.SQLException;
import java.util.ArrayList;
import entidades.Cliente;
import entidades.Factura;

public class DockerFacturaDAO implements FacturaDAO {

	public DockerFacturaDAO() {}

	/**
	 * Devuelve una lista de facturas por cliente.
	 * @param c Que es el cliente.
	 * @return Devuelve una lista de facturas.
	 */
	@Override
	public ArrayList<Factura> list(Cliente c) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Inserta una factura.
	 * @param f Que es la factura.
	 */
	@Override
	public int insert(Factura f) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	public int insertarProducto(Factura f,int a) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
}
