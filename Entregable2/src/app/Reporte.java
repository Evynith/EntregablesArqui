package app;

import java.util.Date;

public class Reporte {

	private String carrera;
	private String estudiante;
	private boolean seGraduo;
	private Date fecha;

	public Reporte(String carrera, String estudiante, boolean seGraduo, Date fecha) {
		this.carrera = carrera;
		this.estudiante = estudiante;
		this.seGraduo = seGraduo;
		this.fecha = fecha;
	}
	
	@Override
	public String toString() {
		return "REPORTE: " + " Carrera: " + this.carrera
				+ " Estudiante: " + this.estudiante
				+ " ¿Graduado?: " + this.seGraduo
				+ " Fecha: " + this.fecha;
	}
}
