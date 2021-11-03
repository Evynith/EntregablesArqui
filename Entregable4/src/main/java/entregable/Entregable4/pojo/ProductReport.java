package entregable.Entregable4.pojo;

import entregable.Entregable4.entidades.Producto;

public class ProductReport {

	private Producto producto;
	private double monto;
	public ProductReport(Producto producto, double monto) {
		super();
		this.producto = producto;
		this.monto = monto;
	}
	public ProductReport() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Producto getProducto() {
		return producto;
	}
	public double getMonto() {
		return monto;
	}
	
	
}
