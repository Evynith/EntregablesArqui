package entidades;

public class EstudianteAMatricular {

	private int libreta;
	private String carrera;
	
	public EstudianteAMatricular() {}
	
	public EstudianteAMatricular(String carrera, int libreta) {
		this.libreta = libreta;
		this.carrera = carrera;
	}

	public int getLibreta() {
		return libreta;
	}

	public String getCarrera() {
		return carrera;
	}
	
	public String toString() {
		return "Libreta: " + this.libreta
				+ " Carrera: " + this.carrera;
	}
}
