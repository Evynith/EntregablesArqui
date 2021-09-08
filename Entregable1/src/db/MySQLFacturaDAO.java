package db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
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
	@Override
	public int insert(Factura f) throws SQLException {
		String insert = "INSERT INTO Invoice(idInvoice, idClient) VALUES (?,?)";
		//preparo la sentencia en la conexion
		PreparedStatement ps = FabricaMysqlDAO.coneccion().prepareStatement(insert);
		ps.setInt(1, f.getId());
		ps.setInt( 2, f.getIdCliente());
		ps.executeUpdate();
		ps.close();
		FabricaMysqlDAO.coneccion().commit();
		return f.getId();
	}
	@Override
	public int insertarProducto(Factura f,int a) throws SQLException {
		String insertFacturaProducto = "INSERT INTO Invoice_Product (IdInvoiceProduct,idInvoice, idProduct, Quantity) VALUES (?,?, ?, ?)";
		PreparedStatement ps = FabricaMysqlDAO.coneccion().prepareStatement(insertFacturaProducto);
		ps.setInt(1, a);
		ps.setInt(2, f.getId());
		ps.setInt(3, f.getIdProducto());
		ps.setInt(4, f.getCantidad());
		ps.executeUpdate();
		ps.close();
		FabricaMysqlDAO.coneccion();
		return f.getId();
	}


}
