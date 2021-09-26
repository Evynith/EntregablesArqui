package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Carrera {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false)
	private String nombre;
	@ManyToMany(fetch = FetchType.LAZY) 
	private List<Estudiante> estudiantes;
	
	public Carrera(String nombre) {
		this.nombre = nombre;
		this.estudiantes = new ArrayList<Estudiante>();
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}
	
	@Override
	public String toString() {
		return getNombre();
	}
}
