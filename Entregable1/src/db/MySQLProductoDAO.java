package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.ClientePorFacturacionDesc;
import entidades.Factura;
import entidades.Producto;
import entidades.ProductoMayorRecaudado;

public class MySQLProductoDAO implements ProductoDAO {
	
	public MySQLProductoDAO() {}

	@Override
	public ArrayList<Producto> list(Factura f) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Producto p) throws SQLException {
		String insert = "INSERT INTO Product(idProduct, name, value) VALUES (?,?,?)";
		//preparo la sentencia en la conexion
		PreparedStatement ps = FabricaMysqlDAO.coneccion().prepareStatement(insert);
		ps.setInt(1, p.getId());
		ps.setString( 2, p.getNombre());
		ps.setFloat(3, p.getValor());
		ps.executeUpdate();
		ps.close();
		FabricaMysqlDAO.coneccion().commit();
		FabricaMysqlDAO.closeConeccion();
	}

	@Override
	public ProductoMayorRecaudado getMayorRecaudacion() throws SQLException {
		String select = "SELECT p.idProduct, p.name, (SUM(p.value) * ip.quantity) AS highestValue"
		+ " FROM Invoice_Product ip JOIN Product p ON (ip.idProduct = p.idProduct)"
		+ " GROUP BY ip.idProduct"
		+ " ORDER BY highestValue DESC"
		+ " LIMIT 1;"; 

		PreparedStatement ps = FabricaMysqlDAO.coneccion().prepareStatement(select);
		ResultSet rs = ps.executeQuery();
		
		ProductoMayorRecaudado pmr = new ProductoMayorRecaudado();
		
		while (rs.next()) {
			pmr = new ProductoMayorRecaudado(rs.getInt(1), rs.getString(2));
		}
		FabricaMysqlDAO.closeConeccion();
		return pmr;
	}

	@Override
	public boolean exists(int idProducto) throws SQLException {
		String select = "SELECT COUNT(*) AS cantidad FROM Product WHERE idProduct = " + idProducto + ";"; 
		PreparedStatement ps = FabricaMysqlDAO.coneccion().prepareStatement(select);
		ResultSet rs = ps.executeQuery();
		boolean rta = false; 
		while (rs.next()) {
			if (rs.getInt(1) > 0 ) {
				rta = true;
			}
		}
		FabricaMysqlDAO.closeConeccion();
		return rta;
	}

}
