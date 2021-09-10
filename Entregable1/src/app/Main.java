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
import db.ClienteDAO;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.mysql.cj.xdevapi.Client;
public class Main {

	public static void main(String[] args) throws SQLException , FileNotFoundException, IOException {
		// creo mi fabrica de fabricas de DAO
		FabricaDAOs MySqlFactory = FabricaDAOs.nuevo(FabricaDAOs.MYSQL_JDBC);
		
		ClienteDAO clientDAO = MySqlFactory.getClienteDAO();
		FacturaDAO invoiceDAO = MySqlFactory.getFacturaDAO();
		ProductoDAO productDAO = MySqlFactory.getProductoDAO();
		
		//Clientes
		CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./data/clientes.csv"));	
		for(CSVRecord row: parser) {
			Cliente c1 = new Cliente(Integer.parseInt(row.get("idCliente")) , row.get("nombre"), row.get("email"));	
			clientDAO.insert(c1);
		}
		//Productos
		CSVParser parser3 = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./data/productos.csv"));
		for(CSVRecord row: parser3) {
			Producto p1 = new Producto( Integer.parseInt(row.get("idProducto")), row.get("nombre"),Float.parseFloat(row.get("valor")));
			productDAO.insert(p1);
		}	
		//Facturas
		ArrayList<Factura> facturas = new ArrayList<Factura>();
		CSVParser parser2 = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./data/facturas.csv"));
		for(CSVRecord row: parser2) {
			Factura f1 = new Factura( Integer.parseInt(row.get("idFactura")), Integer.parseInt(row.get("idCliente")));	
			facturas.add(f1);
		}
		
		CSVParser parser4 = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./data/facturas-productos.csv"));
		for(CSVRecord row: parser4) {
			for(Factura f : facturas ) {
				f.addProducto(Integer.parseInt(row.get("idFactura")), Integer.parseInt(row.get("idProducto")) , Integer.parseInt(row.get("cantidad")) );
			}
		}
		for(Factura f : facturas ) {
			invoiceDAO.insertar(f);
		}
		//----------
			
		ArrayList<ClientePorFacturacionDesc> list = new ArrayList<ClientePorFacturacionDesc>();
		list = clientDAO.list();
		System.out.println(list);
		
		System.out.println(productDAO.getMayorRecaudacion());

	}
	
}
