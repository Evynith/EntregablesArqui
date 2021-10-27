package app;

public class EstudiantesOrdenados {
	
	private String nombre;
	private String apellido;
	private int edad;
	private String genero;
	
	public EstudiantesOrdenados(String nombre, String apellido, int edad, String genero) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public int getEdad() {
		return edad;
	}

	public String getGenero() {
		return genero;
	}
	
	public String toString() {
		return "Nombre: " + this.nombre
				+ " Apellido: " + this.apellido
				+ " Edad: " + this.edad
				+ " Género: " + this.genero;
	}
}
