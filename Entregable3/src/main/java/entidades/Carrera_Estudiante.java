package entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carrera_estudiante")
public class Carrera_Estudiante implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne
	@JoinColumn(insertable = true, nullable = false)
	private Estudiante estudiante;
	@Id
	@ManyToOne 
	@JoinColumn(insertable = true, nullable = false)
	private Carrera carrera; 
	@Column(nullable = false)
	private Date inscripcion = new Date();
	@Column(nullable = true)
	private Date finalizacion;
	
	public Carrera_Estudiante() {}
	 
	public Carrera_Estudiante(Estudiante estudiante, Carrera carrera) {
		this.estudiante = estudiante;
		this.carrera = carrera;
		this.finalizacion = null;
	}
	
	public Carrera_Estudiante(Estudiante e, Carrera c, Date inscripcion) {
		this.carrera = c;
		this.estudiante = e;
		this.inscripcion = inscripcion;
		this.finalizacion = null;
	}
	
	public Estudiante getEstudiante() {
		return this.estudiante;
	}
	
	public Carrera getCarrera() {
		return this.carrera;
	}
	
	public Date getInscripcion() {
		return this.inscripcion;
	}
	
	public Date getFinalizacion() {
		return this.finalizacion;
	}
	
	@Override
	public String toString() {
		return "Carrera_Estudiante [estudiante=" + estudiante + ", carrera=" + carrera + ", inscripcion=" + inscripcion;
	}
}
