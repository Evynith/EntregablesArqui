package db;

import java.sql.SQLException;
import java.util.ArrayList;
import entidades.Cliente;
import entidades.Factura;

public interface FacturaDAO {

	/**
	 * Devuelve un listado de las facturas por cliente.
	 * @return Devuelve una lista de facturas.
	 * @throws SQLException
	 */
	ArrayList<Factura> list(Cliente c) throws SQLException;
		
	/**
	 * Inserta una factura.
	 * @param f Que es la factura.
	 * @throws SQLException
	 */
	int insert(Factura f) throws SQLException;
	int insertarProducto(Factura f) throws SQLException;
}
