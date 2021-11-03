package entregable.Entregable4.pojo;

import java.util.Date;

public class DayReport {

	private String fecha;
	private double total;
	public DayReport() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DayReport(String fecha, double total) {
		super();
		this.fecha = fecha;
		this.total = total;
	}
	public String getFecha() {
		return fecha;
	}
	public double getTotal() {
		return total;
	}
	
	
}
