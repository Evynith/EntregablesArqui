package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Cliente;

public class MySQLClienteDAO implements ClienteDAO {
	
	public MySQLClienteDAO() {
		
	}

	@Override
	public ArrayList<Cliente> list() throws SQLException {
		//traigo datos (prepare statement)
				String select = "SELECT * FROM persona"; //si tubiera un where debo usar el "truco" de los placeolders con los "?"
				//TODO traer los clientes ordenados
				PreparedStatement ps = FabricaDockerDAO.coneccion().prepareStatement(select);
				ResultSet rs = ps.executeQuery();
//				ps.close();
				ArrayList<Cliente> lista = new ArrayList<Cliente>();
				while (rs.next()) {
					lista.add(new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), null));
				}
				//TODO traer las facturas de cada cliente
				//debo devolver un tipo legible porque debo cerrar la conexion, sino se pierde
				FabricaDockerDAO.closeConeccion();
				return lista;
	}

	@Override
	public void insert(Cliente p) throws SQLException {
		String insert = "INSERT INTO cliente(id, nombre, email) VALUES (?,?,?)";
		//preparo la sentencia en la conexion
		PreparedStatement ps = FabricaDockerDAO.coneccion().prepareStatement(insert);
		ps.setInt(1, p.getId());
		ps.setString( 2, p.getNombre());
		ps.setString(3, p.getEmail());
		ps.executeUpdate();
		ps.close();
		FabricaDockerDAO.coneccion().commit();
	}


}
