package db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaDockerDAO extends FabricaDAOs {

	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URI = "jdbc:mysql://localhost:3306/entregable1db";
	public static Connection conn = null;
	
	public FabricaDockerDAO() {}
	
	/**
	 * Crea la conexión a la base de datos.
	 * @return Devuelve la conexón.
	 */
	private static Connection crearConecion() {
		Connection conn;
		//se crea la instancia de driver
		try {
			Class.forName(DRIVER).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			//Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		//creamos conexion (registramos el driver y pedimos la conexion)
		try {
			conn = DriverManager.getConnection(URI, "root", "password");
			conn.setAutoCommit(false);
			return conn;
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Devuelve la conexión y si no la hay la crea.
	 * @return Devuelve la conexión.
	 */
	public static Connection coneccion() {
		if(conn == null) {
			conn = crearConecion();
		}		
		return conn;
	}
	
	/**
	 * Cierra la conexión.
	 * @throws SQLException
	 */
	public static void closeConeccion() throws SQLException {
		conn.close();
		conn = null;
	}

	/**
	 * Devuelve el DAO correspondiente para la tecnología usada.
	 * @return Devuelve un ClienteDAO.
	 */
	@Override
	public ClienteDAO getClienteDAO() {
		return new DockerClienteDAO();
	}

	/**
	 * Devuelve el DAO correspondiente para la tecnología usada.
	 * @return Devuelve una FacturaDAO
	 */
	@Override
	public FacturaDAO getFacturaDAO() {
		return new DockerFacturaDAO();
	}

	/**
	 * Devuelve el DAO correspondiente para la tecnología usada.
	 * @return Devuelve un ProductoDAO.
	 */
	@Override
	public ProductoDAO getProductoDAO() {
		return new DockerProductoDAO();
	}
}
