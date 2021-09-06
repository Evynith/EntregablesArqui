package entidades;

import java.util.ArrayList;

public class Factura {

	private int id;
	private ArrayList<Producto> productos;
	
	public Factura(int id, ArrayList<Producto> productos) {
		this.id = id;
		this.productos = new ArrayList<Producto>();
		this.productos.addAll(productos);
	}

	public int getId() {
		return id;
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}
	
	public void addProducto(Producto p) {
		this.productos.add(p);
	}

	@Override
	public String toString() {
		return "Factura [id=" + id + ", productos=" + productos + "]";
	}
	
	
}
