package db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaDerbyDAO extends FabricaDAOs {

	public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String URI = "jdbc:derby:entregable1db;create=true";
	public static Connection conn = null;
	
	public FabricaDerbyDAO() {}
	
	/**
	 * Crea la conexi�n a la base de datos.
	 * @return Devuelve la conex�n.
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
	 * Devuelve la conexi�n y si no la hay la crea.
	 * @return Devuelve la conexi�n.
	 */
	public static Connection coneccion() {
		if(conn == null) {
			conn = crearConecion();
		}		
		return conn;
	}
	
	/**
	 * Cierra la conexi�n.
	 * @throws SQLException
	 */
	public static void closeConeccion() throws SQLException {
		conn.close();
		conn = null;
	}
	
	/**
	 * Devuelve el DAO correspondiente para la tecnolog�a usada.
	 * @return Devuelve un ClienteDAO.
	 */
	@Override
	public ClienteDAO getClienteDAO() {
		//return new DerbyClienteDAO();
		return null;
	}

	/**
	 * Devuelve el DAO correspondiente para la tecnolog�a usada.
	 * @return Devuelve una FacturaDAO
	 */
	@Override
	public FacturaDAO getFacturaDAO() {
		//return new DerbyFacturaDAO();
		return null;
	}

	/**
	 * Devuelve el DAO correspondiente para la tecnolog�a usada.
	 * @return Devuelve un ProductoDAO.
	 */
	@Override
	public ProductoDAO getProductoDAO() {
		//return new DerbyProductoDAO();
		return null;
	}
}
