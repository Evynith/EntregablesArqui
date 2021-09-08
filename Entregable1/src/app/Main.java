package app;

import java.sql.SQLException;
import db.FabricaDAOs;
import db.FacturaDAO;
import db.ProductoDAO;
import entidades.Cliente;
import entidades.Factura;
import entidades.Producto;
import db.ClienteDAO;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
public class Main {

	public static void main(String[] args) throws SQLException , FileNotFoundException, IOException {
		// creo mi fabrica de fabricas de DAO
		FabricaDAOs MySqlFactory = FabricaDAOs.nuevo(FabricaDAOs.MYSQL_JDBC);
		
		ClienteDAO clientDAO = MySqlFactory.getClienteDAO();
		FacturaDAO invoiceDAO = MySqlFactory.getFacturaDAO();
		ProductoDAO productDAO = MySqlFactory.getProductoDAO();
		CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("/home/tubino/eclipse-workspace/EntregasArqui2021/Entregable1arquitecturas/data/clientes.csv"));	
		for(CSVRecord row: parser) {
			Cliente c1 = new Cliente(Integer.parseInt(row.get("idCliente")) , row.get("nombre"), row.get("email"));
			int idClient = clientDAO.insert(c1);
		}
		
		
		
		CSVParser parser2 = CSVFormat.DEFAULT.withHeader().parse(new FileReader("/home/tubino/eclipse-workspace/EntregasArqui2021/Entregable1arquitecturas/data/facturas.csv"));
		for(CSVRecord row: parser2) {
			Factura f1 = new Factura( Integer.parseInt(row.get("idFactura")), Integer.parseInt(row.get("idCliente")) );
			int idInvoice = invoiceDAO.insert(f1);
			}	
		
		CSVParser parser3 = CSVFormat.DEFAULT.withHeader().parse(new FileReader("/home/tubino/eclipse-workspace/EntregasArqui2021/Entregable1arquitecturas/data/productos.csv"));
		for(CSVRecord row: parser3) {
			Producto p1 = new Producto( Integer.parseInt(row.get("idProducto")), row.get("nombre"),Integer.parseInt(row.get("valor")) );

			productDAO.insert(p1);	}	
		
		int aux = 1;
		
		CSVParser parser4 = CSVFormat.DEFAULT.withHeader().parse(new FileReader("/home/tubino/eclipse-workspace/EntregasArqui2021/Entregable1arquitecturas/data/facturas-productos.csv"));;
		for(CSVRecord row: parser4) {
//			System.out.println("   id fact   "+Integer.parseInt(row.get("idFactura"))+"   id prod  "+Integer.parseInt(row.get("idProducto"))+"  cantidad  "+Integer.parseInt(row.get("cantidad")));
			Factura f1 = new Factura( aux,Integer.parseInt(row.get("idFactura")), Integer.parseInt(row.get("idProducto")) , Integer.parseInt(row.get("cantidad")) );
			int idInvoice = invoiceDAO.insertarProducto(f1,aux);
			aux++;
		}	
//		Factura f1 = new Factura(1, idClient);
//		
//		int idInvoice = invoiceDAO.insert(f1);
//		
//		
//		Producto p1 = new Producto(1, "Alfajor", 50, 1, idInvoice);
//		
//		productDAO.insert(p1);
	}
}
