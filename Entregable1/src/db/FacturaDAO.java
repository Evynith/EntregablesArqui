package db;

import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Cliente;
import entidades.Factura;

public interface FacturaDAO {

	//devuelve un listado de facturas de la db de determinado cliente
		public ArrayList<Factura> list(Cliente c) throws SQLException;
		
		//inserta en la db una factura con los datos dados
		public void insert(Factura f) throws SQLException;
}
