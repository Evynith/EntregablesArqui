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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "carrera")
public class Carrera {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false)
	private String nombre;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "carrera") 
	private List<Carrera_Estudiante> estudiantes;
	
	public Carrera(String nombre) {
		this.nombre = nombre;
		this.estudiantes = new ArrayList<Carrera_Estudiante>();
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
	
	@Override
	public String toString() {
		return getNombre();
	}
}
