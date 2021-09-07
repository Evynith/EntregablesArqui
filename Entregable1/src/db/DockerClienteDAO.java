package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidades.Cliente;
import entidades.ClientePorFacturacionDesc;

public class DockerClienteDAO implements ClienteDAO {

	public DockerClienteDAO() {}

	/**
	 * Devuelve un listado de los clientes ordenados por mayor facturación.
	 * @return Devuelve una lista de clientes.
	 * @throws SQLException
	 */
	@Override
	public ArrayList<ClientePorFacturacionDesc> list() throws SQLException {
		//traigo datos (prepare statement)
		String select = "SELECT *, SUM(p.value) AS totalValue"
		+ "FROM Client c JOIN Invoice i ON (c.idClient = i.idClient)"
		+ "JOIN Invoice_Product ip ON (i.idInvoice = ip.idInvoice)"
		+ "JOIN Product p ON (ip.idProduct = p.idProduct)"
		+ "ORDER BY totalValue DESC;";
		//si tuviera un where debo usar el "truco" de los placeolders con los "?"
		PreparedStatement ps = FabricaDockerDAO.coneccion().prepareStatement(select);
		ResultSet rs = ps.executeQuery();
		//debo devolver un tipo legible porque debo cerrar la conexion, sino se pierde
		ArrayList<ClientePorFacturacionDesc> lista = new ArrayList<ClientePorFacturacionDesc>();
		while (rs.next()) {
			lista.add(new ClientePorFacturacionDesc(rs.getInt(1), rs.getString(2), rs.getFloat(3)));
		}
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
		String insert = "INSERT INTO Client(id, nombre, email) VALUES (?,?,?)";
		//preparo la sentencia en la conexion
		PreparedStatement ps = FabricaDockerDAO.coneccion().prepareStatement(insert);
		ps.setInt(1, p.getId());
		ps.setString( 2, p.getNombre());
		ps.setString(3, p.getEmail());
		ps.executeUpdate();
		ps.close();
		FabricaDockerDAO.coneccion().commit();
		return p.getId();
	}

}
