package db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class TablasMySql {

	public TablasMySql() throws SQLException {	
		createTables();
	}
	
	private void ejecutar(String query) throws SQLException {
		Connection connection = FabricaDockerDAO.coneccion();
		connection.prepareStatement(query).execute();
		connection.commit();
		FabricaDockerDAO.closeConeccion();
	}
	
	private void createTables() throws SQLException {
		deleteTables();
		createClientTable();
		createInvoiceTable();
		createProductTable();
		createInvoiceProductTable();
	}
	
	private void createClientTable() throws SQLException {
		String clientTable = "Client";
		String query = "CREATE TABLE " + clientTable + "(" +
		"idClient INT NOT NULL," +
		"name VARCHAR(500) NOT NULL," +
		"email VARCHAR(150) NOT NULL," +
		"PRIMARY KEY(idClient))";
		
		this.ejecutar(query);
	}
	
	private void createInvoiceTable() throws SQLException {
		String invoiceTable = "Invoice";
		String query = "CREATE TABLE " + invoiceTable + "(" +
		"idInvoice INT NOT NULL," +
		"idClient INT NOT NULL," +
		"PRIMARY KEY(idInvoice)," +
		"FOREIGN KEY(idClient) REFERENCES Client(idClient))";
		
		this.ejecutar(query);
	}
	
	public void createProductTable() throws SQLException {
		String productTable = "Product";
		String query = "CREATE TABLE " + productTable + "(" +
		"idProduct INT NOT NULL," +
		"name VARCHAR(45) NOT NULL," +
		"value FLOAT NOT NULL," +
		"PRIMARY KEY(idProduct))"; 
	
		this.ejecutar(query);
	}
	
	private void createInvoiceProductTable() throws SQLException {
		String invoiceProductTable = "Invoice_Product";
		String query = "CREATE TABLE " + invoiceProductTable + "(" +
		"IdInvoiceProduct INT NOT NULL," +
		"idInvoice INT NOT NULL," +
		"idProduct INT NOT NULL," +
		"Quantity INT NOT NULL," +
		"PRIMARY KEY(idInvoiceProduct)," +
		"FOREIGN KEY(idInvoice) REFERENCES Invoice(idInvoice)," +
		"FOREIGN KEY(idProduct) REFERENCES Product(idProduct))";
		
		this.ejecutar(query);
	}
	
	private void deleteTables() throws SQLException {
		String queryInvoiceProduct = "DROP TABLE IF EXISTS Invoice_Product";
		String queryProduct = "DROP TABLE IF EXISTS Product";
		String queryInvoice = "DROP TABLE IF EXISTS Invoice";
		String queryClient = "DROP TABLE IF EXISTS Client";
		
		ArrayList<String> querys = new ArrayList<String>();
		querys.add(queryInvoice);
		querys.add(queryClient);
		querys.add(queryInvoiceProduct);
		querys.add(queryProduct);

		for (String query : querys) {
			this.ejecutar(query);		
		}
	}
	
}
