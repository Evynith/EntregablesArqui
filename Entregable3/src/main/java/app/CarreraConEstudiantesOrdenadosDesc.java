package app;

public class CarreraConEstudiantesOrdenadosDesc {

	private String carrera;
	private long cantidadEstudiantes;
	
	public CarreraConEstudiantesOrdenadosDesc(String carrera, long cantidadEstudiantes) {
		this.carrera = carrera;
		this.cantidadEstudiantes = cantidadEstudiantes;
	}
	
	public String getCarrera() {
		return this.carrera;
	}
	
	public long getCantidadEstudiantes() {
		return this.cantidadEstudiantes;
	}
	
	public String toString() {
		return "Carrera: " + this.carrera + " Cantidad de estudiantes: " + this.cantidadEstudiantes;
	}
}
