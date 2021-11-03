package entregable.Entregable4.pojo;

import java.util.Date;

public class DayReport {

	private Date fecha;
	private double total;
	public DayReport() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DayReport(Date fecha, double total) {
		super();
		this.fecha = fecha;
		this.total = total;
	}
	public Date getFecha() {
		return fecha;
	}
	public double getTotal() {
		return total;
	}
	
	
}
