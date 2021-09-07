package db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaMysqlDAO extends FabricaDAOs {

	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URI = "jdbc:mysql://localhost:3306/entregable1db";
	public static Connection conn = null;
	
	public FabricaMysqlDAO() {
		try {
			this.createTables();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
			conn = DriverManager.getConnection(URI, "root", "");
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
	 * Crea una instancia para crear las tablas de la base de datos.
	 * @throws SQLException
	 */
	private void createTables() throws SQLException {
		new TablasMySql();
	}
	
	/**
	 * Devuelve el DAO correspondiente para la tecnología usada.
	 * @return Devuelve un ClienteDAO.
	 */
	@Override
	public ClienteDAO getClienteDAO() {
		return new MySQLClienteDAO();
	}

	/**
	 * Devuelve el DAO correspondiente para la tecnología usada.
	 * @return Devuelve una FacturaDAO
	 */
	@Override
	public FacturaDAO getFacturaDAO() {
		return new MySQLFacturaDAO();
	}

	/**
	 * Devuelve el DAO correspondiente para la tecnología usada.
	 * @return Devuelve un ProductoDAO.
	 */
	@Override
	public ProductoDAO getProductoDAO() {
		return new MySQLProductoDAO();
	}
}
