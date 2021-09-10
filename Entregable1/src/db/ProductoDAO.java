package db;

import java.sql.SQLException;
import java.util.ArrayList;
import entidades.Factura;
import entidades.Producto;
import entidades.ProductoMayorRecaudado;

public interface ProductoDAO {
	
	/**
	 * Devuelve una lista de productos por factura.
	 * @param f Que es la factura.
	 * @return Devuelve una lista de productos.
	 * @throws SQLException
	 */
	ArrayList<Producto> list(Factura f) throws SQLException;
	
	/**
	 * Inserta producto.
	 * @param p Que es el producto
	 * @throws SQLException
	 */
	void insert(Producto p) throws SQLException;
	
	/**
	 * Devuelve el producto que mayor recaudación hizo.
	 * @return Devuelve un producto con mayor recaudación.
	 * @throws SQLException
	 */
	ProductoMayorRecaudado getMayorRecaudacion() throws SQLException;
	
	boolean exists(int idProducto) throws SQLException;
}
