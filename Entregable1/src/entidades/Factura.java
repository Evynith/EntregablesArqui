package entidades;

public class Factura {

	private int id;
	private int idCliente;
	private int cantidad;
	private int idProducto;
	private int idFactura_producto;
	public Factura(int id, int idCliente) {
		this.id = id;
		this.idCliente = idCliente;
	}
	public Factura(int idFact_Prod,int idFactura, int idProducto,int cantidad) {
		this.id = idFactura;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.idFactura_producto = idFact_Prod;

	}
	public int getId() {
		return id;
	}

	public int getIdCliente() {
		return this.idCliente;
	}
	public int getIdFactura_producto() {
		return this.idFactura_producto;
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
