package app;

public class ReporteEstudiante {
	
	private String carrera;
	private int anio;
	private int inscriptos;
	private int egresados;
	
	public ReporteEstudiante(String carrera, int anio, int inscriptos, int egresados) {
		this.carrera = carrera;
		this.anio = anio;
		this.inscriptos = inscriptos;
		this.egresados = egresados;
	}
	

	@Override
	public String toString() {
		return "Carrera: " + this.carrera
				+ "Año: " + this.anio
				+ "Inscriptos: " + this.inscriptos
				+ "Egresados: " + this.egresados;
	}
}
