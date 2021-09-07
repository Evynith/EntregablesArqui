package app;

import java.sql.SQLException;
import db.FabricaDAOs;
import db.FacturaDAO;
import db.ProductoDAO;
import entidades.Cliente;
import entidades.Factura;
import entidades.Producto;
import db.ClienteDAO;

public class Main {

	public static void main(String[] args) throws SQLException {
		// creo mi fabrica de fabricas de DAO
		FabricaDAOs MySqlFactory = FabricaDAOs.nuevo(FabricaDAOs.MYSQL_JDBC);
		ClienteDAO clientDAO = MySqlFactory.getClienteDAO();
		
		Cliente c1 = new Cliente(1, "Fede", "fede@gmail.com");
		
		int idClient = clientDAO.insert(c1);
		
		FacturaDAO invoiceDAO = MySqlFactory.getFacturaDAO();
		
		Factura f1 = new Factura(1, idClient);
		
		int idInvoice = invoiceDAO.insert(f1);
		
		ProductoDAO productDAO = MySqlFactory.getProductoDAO();
		
		Producto p1 = new Producto(1, "Alfajor", 50, 1, idInvoice);
		
		productDAO.insert(p1);
	}
}
