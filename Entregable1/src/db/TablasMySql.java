package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class TablasMySql {
	
	private String Client;
	private String Invoice;
	private String Product;
	private String InvoiceProduct;

	public TablasMySql() throws SQLException {
		this.Client = "Client";
		this.Invoice = "Invoice";
		this.Product = "Product";
		this.InvoiceProduct = "Invoice_Product";
		createTables();
	}
	
	/**
	 * Abre la conexión a MySQL, prepara la consulta y cierra la conexión.
	 * @param query Que es la consulta SQL.
	 * @throws SQLException
	 */
	private void ejecutar(String query) throws SQLException {
		Connection connection = FabricaMysqlDAO.coneccion();
		connection.prepareStatement(query).execute();
		connection.commit();
		FabricaMysqlDAO.closeConeccion();
	}
	
	/**
	 * Borra las tablas y las crea en la base de datos.
	 * @throws SQLException
	 */
	private void createTables() throws SQLException {
		deleteTables();
		createClientTable();
		createInvoiceTable();
		createProductTable();
		createInvoiceProductTable();
	}
	
	/**
	 * Crea la tabla del cliente.
	 * @throws SQLException
	 */
	private void createClientTable() throws SQLException {
		String query = "CREATE TABLE " + this.Client + "(" +
		"idClient INT NOT NULL," +
		"name VARCHAR(500) NOT NULL," +
		"email VARCHAR(150) NOT NULL," +
		"PRIMARY KEY(idClient))";
		
		this.ejecutar(query);
	}
	
	/**
	 * Crea la tabla de factura.
	 * @throws SQLException
	 */
	private void createInvoiceTable() throws SQLException {
		String query = "CREATE TABLE " + this.Invoice + "(" +
		"idInvoice INT NOT NULL," +
		"idClient INT NOT NULL," +
		"PRIMARY KEY(idInvoice)," +
		"FOREIGN KEY(idClient) REFERENCES Client(idClient))";
		
		this.ejecutar(query);
	}
	
	/**
	 * Crea la tabla de producto.
	 * @throws SQLException
	 */
	public void createProductTable() throws SQLException {
		String query = "CREATE TABLE " + this.Product + "(" +
		"idProduct INT NOT NULL," +
		"name VARCHAR(45) NOT NULL," +
		"value FLOAT NOT NULL," +
		"PRIMARY KEY(idProduct))"; 
	
		this.ejecutar(query);
	}
	
	/**
	 * Crea la tabla de facturas por producto.
	 * @throws SQLException
	 */
	private void createInvoiceProductTable() throws SQLException {
		String query = "CREATE TABLE " + this.InvoiceProduct + "(" +
		"idInvoice INT NOT NULL," +
		"idProduct INT NOT NULL," +
		"Quantity INT NOT NULL," +
		"PRIMARY KEY(idInvoice, idProduct)," +
		"FOREIGN KEY(idInvoice) REFERENCES Invoice(idInvoice)," +
		"FOREIGN KEY(idProduct) REFERENCES Product(idProduct))";
		
		this.ejecutar(query);
	}
	
	/**
	 * Borra las tablas.
	 * @throws SQLException
	 */
	private void deleteTables() throws SQLException {
		String queryInvoiceProduct = "DROP TABLE IF EXISTS " + this.InvoiceProduct;
		String queryProduct = "DROP TABLE IF EXISTS " + this.Product;
		String queryInvoice = "DROP TABLE IF EXISTS " + this.Invoice;
		String queryClient = "DROP TABLE IF EXISTS " + this.Client;
		
		ArrayList<String> querys = new ArrayList<String>();
		querys.add(queryInvoiceProduct);
		querys.add(queryProduct);
		querys.add(queryInvoice);
		querys.add(queryClient);

		for (String query : querys) {
			this.ejecutar(query);		
		}
	}
	
}
