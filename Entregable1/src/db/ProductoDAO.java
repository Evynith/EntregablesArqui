package db;

import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Cliente;
import entidades.Factura;
import entidades.Producto;

public interface ProductoDAO {
	//devuelve un listado de productos de la db de determinada factura
			public ArrayList<Producto> list(Factura f) throws SQLException;
			
			//inserta en la db un producto con los datos dados
			public void insert(Producto p) throws SQLException;
			
			public Producto getMayorRecaudacion() throws SQLException;
}
