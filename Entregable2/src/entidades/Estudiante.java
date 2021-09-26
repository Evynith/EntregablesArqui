package entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Estudiante {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int dni;
	@Column(nullable = false)
	private int libreta;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String apellido;
	@Column(nullable = false)
	private String genero;
	@Column(nullable = false)
	private int edad;
	@Column(nullable = false)
	private String ciudad;
	@ManyToMany(mappedBy = "estudiantes", fetch = FetchType.LAZY)
	private List<Carrera> carreras;
	
	public Estudiante(int libreta, String nombre, String apellido, 
		String genero, int edad, String ciudad) {
		this.libreta = libreta;
		this.nombre = nombre;
		this.apellido = apellido;
		this.genero = genero;
		this.edad = edad;
		this.ciudad = ciudad;
		this.carreras = new ArrayList<Carrera>();
	}
	
	public void agregarCarrera(Carrera c) {
		this.carreras.add(c);
	}

	public int getDni() {
		return dni;
	}

	public int getLibreta() {
		return libreta;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getGenero() {
		return genero;
	}

	public int getEdad() {
		return edad;
	}

	public String getCiudad() {
		return ciudad;
	}
	
	@Override
	public String toString() {
		return getNombre() + " | " + getDni();
	}
}
