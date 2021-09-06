package db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FabricaDerbyDAO extends FabricaDAOs {

	public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String URI = "jdbc:derby:entregable1db;create=true";
	public static Connection conn = null;
	
	public FabricaDerbyDAO() {	
	}
	
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
//			conn = null;
			e.printStackTrace();
			return null;
		}
//		return conn;
	}
	
	public static Connection coneccion() {
		if(conn == null) {
			conn = crearConecion();
		}		
		return conn;
	}
	
	public static void closeConeccion() throws SQLException {
		conn.close();
		conn = null;
	}
	
	/*
	 * LO MISMO QUE LOS OTROS PORQUE TAMBIÉN USAN JDBC!!!!!!!!!!!!!!
	 */
	
	public ClienteDAO getClienteDAO() {
//		return new DerbyClienteDAO();
		//TODO
		return null;
	}
	
	public FacturaDAO getFacturaDAO() {
//		return new DerbyFacturaDAO();
		//TODO
		return null;
	}
	
	public ProductoDAO getProductoDAO() {
//		return new DerbyProductoDAO();
		//TODO
		return null;
	}
		
		
}
