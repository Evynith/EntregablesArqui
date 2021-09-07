package app;

import java.sql.SQLException;
import java.util.ArrayList;

import db.FabricaDAOs;
import db.FacturaDAO;
import db.ProductoDAO;
import entidades.Cliente;
import entidades.ClientePorFacturacionDesc;
import entidades.Factura;
import entidades.Producto;
import entidades.ProductoMayorRecaudado;
import db.ClienteDAO;

public class Main {

	public static void main(String[] args) throws SQLException {
		// creo mi fabrica de fabricas de DAO
		FabricaDAOs MySqlFactory = FabricaDAOs.nuevo(FabricaDAOs.MYSQL_JDBC);
		ClienteDAO clientDAO = MySqlFactory.getClienteDAO();
		
		Cliente c1 = new Cliente(1, "Fede", "fede@gmail.com");
		Cliente c2 = new Cliente(2, "Fede2", "fede@gmail.com2");
		Cliente c3 = new Cliente(3, "Fede3", "fede@gmail.com3");
		
		int idClient = clientDAO.insert(c1);
		int idClient2 = clientDAO.insert(c2);
		int idClient3 = clientDAO.insert(c3);
		
		FacturaDAO invoiceDAO = MySqlFactory.getFacturaDAO();
		
		Factura f1 = new Factura(1, idClient);
		Factura f2 = new Factura(2, idClient2);
		Factura f3 = new Factura(3, idClient3);
		
		int idInvoice = invoiceDAO.insert(f1);
		int idInvoice2 = invoiceDAO.insert(f2);
		int idInvoice3 = invoiceDAO.insert(f3);
		
		ProductoDAO productDAO = MySqlFactory.getProductoDAO();
		
		Producto p1 = new Producto(1, "Alfajor", 50, 10, idInvoice);
		Producto p2 = new Producto(2, "Coca cola", 200, 1, idInvoice);
		Producto p3 = new Producto(3, "Bizcochotos", 75, 60, idInvoice2);
		Producto p4 = new Producto(4, "Agua mineral", 1, 100, idInvoice3);
		
		productDAO.insert(p1);
		productDAO.insert(p2);
		productDAO.insert(p3);
		productDAO.insert(p4);
		
		ProductoMayorRecaudado pmr = productDAO.getMayorRecaudacion();
		System.out.println(pmr);
		
		ArrayList<ClientePorFacturacionDesc> listado = new ArrayList<ClientePorFacturacionDesc>();
		
		listado = clientDAO.list();
		
		System.out.println(listado);
	}
}
