package entidades;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "estudiante")
@NamedQuery(name = Estudiante.OBTENER_TODOS_ORDENADOS, query = "SELECT e FROM Estudiante e ORDER BY e.edad")
public class Estudiante {

	public static final String OBTENER_TODOS_ORDENADOS = "Estudiante.OBTENER_TODOS_ORDENADOS";
	
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
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "estudiante")
	private List<Carrera_Estudiante> carreras;
	
	public Estudiante() {}
	
	public Estudiante(int libreta, String nombre, String apellido, 
		String genero, int edad, String ciudad) {
		this.libreta = libreta;
		this.nombre = nombre;
		this.apellido = apellido;
		this.genero = genero;
		this.edad = edad;
		this.ciudad = ciudad;
		this.carreras = new ArrayList<Carrera_Estudiante>();
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
