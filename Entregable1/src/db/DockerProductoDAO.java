package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidades.Factura;
import entidades.Producto;
import entidades.ProductoMayorRecaudado;

public class DockerProductoDAO implements ProductoDAO {

	public DockerProductoDAO() {}

	/**
	 * Devuelve una lista de productos que tiene la factura.
	 * @param f Que es la factura.
	 * @return Devuelve una lista de productos.
	 */
	@Override
	public ArrayList<Producto> list(Factura f) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Inserta un producto.
	 * @param p Que es el producto.
	 */
	@Override
	public void insert(Producto p) throws SQLException {
		// TODO Auto-generated method stub
	}

	/***
	 * Devuelve el producto con más recaudación.
	 * @return Devuelve el producto que más recaudó.
	 */
	@Override
	public ProductoMayorRecaudado getMayorRecaudacion() throws SQLException {
		String select = "SELECT *, (SUM(p.value) * ip.quantity) AS highestValue"
				+ "FROM Invoice_Product ip JOIN Product p ON (ip.idProduct = p.idProduct)"
				+ "GROUP BY ip.idProduct"
				+ "ORDER BY highestValue DESC"
				+ "LIMIT 1;"; 

		PreparedStatement ps = FabricaMysqlDAO.coneccion().prepareStatement(select);
		ResultSet rs = ps.executeQuery();

		ProductoMayorRecaudado pmr = new ProductoMayorRecaudado(rs.getInt(0), rs.getString(1));
		FabricaMysqlDAO.closeConeccion();
		return pmr;
	}

	@Override
	public boolean exists(int idProducto) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
}
