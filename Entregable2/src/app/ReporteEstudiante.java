package app;

public class ReporteEstudiante {
	
	private String carrera;
	private String estudiante;
	private int anioInscripcion;
	private Integer anioFinalizacion;
	
	public ReporteEstudiante(String carrera, String estudiante, int anioInscripcion, Integer anioFinalizacion) {
		this.carrera = carrera;
		this.estudiante = estudiante;
		this.anioInscripcion = anioInscripcion;
		this.anioFinalizacion = anioFinalizacion;
	}
	
	@Override
	public String toString() {
		return "Carrera: " + this.carrera
				+ ": " + this.estudiante
				+ " - A�o inscripci�n: " + this.anioInscripcion
				+ " - A�o finalizaci�n: " + this.anioFinalizacion;
	}
}
