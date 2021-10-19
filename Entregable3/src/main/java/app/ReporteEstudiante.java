package app;

public class ReporteEstudiante {
	
	private String carrera;
	private Integer anio;
	private long inscriptos;
	private long egresados;
	
	public ReporteEstudiante(String carrera, Integer anio, long inscriptos, long egresados) {
		this.carrera = carrera;
		this.anio = anio;
		this.egresados = egresados;
		this.inscriptos = inscriptos;
	}
	
	public String getCarrera() {
		return carrera;
	}

	public Integer getAnio() {
		return anio;
	}

	public long getInscriptos() {
		return inscriptos;
	}

	public long getEgresados() {
		return egresados;
	}

	@Override
	public String toString() {
		return "Carrera: " + this.carrera
				+ " Año: " + this.anio
				+ " Inscriptos: " + this.inscriptos
				+ " Egresados: " + this.egresados;
	}
}
