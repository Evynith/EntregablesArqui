package entregable.Entregable4.pojo;

public class ProductLimit {

	private String fecha;
	private int cantidadActual;
	public ProductLimit() {
		super();
	}
	public ProductLimit(String fecha, long cantidadActual) {
		super();
		this.fecha = fecha;
		this.cantidadActual = Math.toIntExact(cantidadActual);
	}
	public String getFecha() {
		return fecha;
	}
	public int getCantidadActual() {
		return cantidadActual;
	}
	
	
}
