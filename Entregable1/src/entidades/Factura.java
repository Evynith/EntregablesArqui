package entidades;

import java.util.Hashtable;

public class Factura {

	private int id;
	private int idCliente;
	private Hashtable<Integer, Integer> productos;

	public Factura(int id, int idCliente) {
		this.id = id;
		this.idCliente = idCliente;
		this.productos = new Hashtable<Integer, Integer>();
	}
	
	public void addProducto(int producto, int cantidad, int idFactura) {
		if (idFactura == this.id) {
			this.productos.put(producto, cantidad);	
		}
	}
	
	public Hashtable<Integer, Integer> getProductos() {
		return new Hashtable<Integer, Integer>(this.productos);
	}
	
	public int getId() {
		return id;
	}
	
	public int getIdCliente() {
		return idCliente;
	}

	@Override
	public String toString() {
		return "Factura [id=" + id +"]";
	}
}
