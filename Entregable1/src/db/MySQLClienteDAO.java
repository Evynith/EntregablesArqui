package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Cliente;
import entidades.ClientePorFacturacionDesc;

public class MySQLClienteDAO implements ClienteDAO {
	
	public MySQLClienteDAO() {}
	
	/**
	 * Devuelve un listado de los clientes ordenados por mayor facturación.
	 * @return Devuelve una lista de clientes.
	 * @throws SQLException
	 */
	@Override
	public ArrayList<ClientePorFacturacionDesc> list() throws SQLException {
		String select = "SELECT *, SUM(p.value) AS totalValue"
		+ "FROM Client c JOIN Invoice i ON (c.idClient = i.idClient)"
		+ "JOIN Invoice_Product ip ON (i.idInvoice = ip.idInvoice)"
		+ "JOIN Product p ON (ip.idProduct = p.idProduct)"
		+ "ORDER BY totalValue DESC;"; 
		//traigo datos (prepare statement)
		//si tuviera un where debo usar el "truco" de los placeolders con los "?"
		PreparedStatement ps = FabricaMysqlDAO.coneccion().prepareStatement(select);
		ResultSet rs = ps.executeQuery();
		ArrayList<ClientePorFacturacionDesc> lista = new ArrayList<ClientePorFacturacionDesc>();
		while (rs.next()) {
			lista.add(new ClientePorFacturacionDesc(rs.getInt(1), rs.getString(2), rs.getString(3)));
		}
		//debo devolver un tipo legible porque debo cerrar la conexion, sino se pierde
		FabricaDockerDAO.closeConeccion();
		return lista;
	}

	/**
	 * Inserta un cliente.
	 * @param p Que es el cliente.
	 * @throws SQLException
	 */
	@Override
	public int insert(Cliente p) throws SQLException {
		String insert = "INSERT INTO Client(idClient, name, email) VALUES (?,?,?)";
		//preparo la sentencia en la conexion
		PreparedStatement ps = FabricaMysqlDAO.coneccion().prepareStatement(insert);
		ps.setInt(1, p.getId());
		ps.setString( 2, p.getNombre());
		ps.setString(3, p.getEmail());
		ps.executeUpdate();
		ps.close();
		FabricaMysqlDAO.coneccion().commit();
		return p.getId();
	}
}
