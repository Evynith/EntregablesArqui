package entregable.Entregable4.pojo;

public class ClientReport {

	private String nombre;
	private double total;
	
	public ClientReport() {}
	
	public ClientReport(String nombre, double total) {
		this.nombre = nombre;
		this.total = total;
	}

	public String getNombre() {
		return nombre;
	}

	public double getTotal() {
		return total;
	}


}
