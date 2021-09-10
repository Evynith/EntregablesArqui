package db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import entidades.Cliente;
import entidades.Factura;

public class MySQLFacturaDAO implements FacturaDAO {
	
	public MySQLFacturaDAO() {}

	/**
	 * Devuelve un listado de las facturas por cliente.
	 * @return Devuelve una lista de facturas.
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Factura> list(Cliente c) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Inserta una factura.
	 * @param f Que es la factura.
	 * @throws SQLException
	 */
	
	private void insertarFactura(Factura f) throws SQLException {
		String insert = "INSERT INTO Invoice(idInvoice, idClient) VALUES (?,?)";
		//preparo la sentencia en la conexion
		PreparedStatement ps = FabricaMysqlDAO.coneccion().prepareStatement(insert);
		ps.setInt(1, f.getId());
		ps.setInt( 2, f.getIdCliente());
		ps.executeUpdate();
		ps.close();
		FabricaMysqlDAO.coneccion().commit();
	}
	//TODO revisar violacion en FK desde los CSV, vienen inconsistentes
	private void insertarProductoEnFactura(int idProducto, int cantidad, int idFactura) throws SQLException {
		String insertFacturaProducto = "INSERT INTO Invoice_Product(idInvoice, idProduct, Quantity) VALUES (?, ?, ?)";
//		System.out.println("... " + idFactura+ "-" + idProducto + "-" + cantidad + "...");
		PreparedStatement ps = FabricaMysqlDAO.coneccion().prepareStatement(insertFacturaProducto);
		ps.setInt(1, idFactura);
		ps.setInt(2, idProducto);
		ps.setInt(3, cantidad);
		ps.executeUpdate();
		ps.close();
		FabricaMysqlDAO.coneccion();
	}
	
	private void insertarProductosEnFactura(Factura f) throws SQLException {
		Hashtable<Integer, Integer> productos = f.getProductos();
		productos.forEach((k,v) -> {
			try {
				this.insertarProductoEnFactura( k,v,f.getId() );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	@Override
	public int insertar(Factura f) throws SQLException {
		this.insertarFactura(f);
		this.insertarProductosEnFactura(f);
		return f.getId();
	}


}
