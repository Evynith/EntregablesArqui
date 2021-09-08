package entidades;

public class Factura {

	private int id;
	private int idCliente;
	private int cantidad;
	private int idProducto;

	public Factura(int id, int idCliente) {
		this.id = id;
		this.idCliente = idCliente;
	}
	
	public Factura(int id, int idProducto, int cantidad) {
		this.id = id;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}
	
	public int getId() {
		return id;
	}

	public int getIdCliente() {
		return this.idCliente;
	}

	public int getCantidad() {
		return this.cantidad;
	}
	public int getIdProducto() {
		return this.idProducto;
	}

	@Override
	public String toString() {
		return "Factura [id=" + id +"]";
	}
}
