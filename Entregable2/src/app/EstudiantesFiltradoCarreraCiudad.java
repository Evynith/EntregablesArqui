package app;

import entidades.Estudiante;

public class EstudiantesFiltradoCarreraCiudad {

	private String carrera;
	private Estudiante estudiante;
	
	public EstudiantesFiltradoCarreraCiudad(String carrera, Estudiante estudiante) {
		this.carrera = carrera;
		this.estudiante = estudiante;
	}
	
	public String toString() {
		return "Carrera: " + this.carrera
				+ " Estudiante:" + this.estudiante;
	}
}
