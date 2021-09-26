package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	 private Date inscripcion;
	 @Column(nullable = false)
	 private boolean seGraduo;
	 
	 public Carrera_Estudiante(Estudiante e, Carrera c) {
		 this.carrera = c;
		 this.estudiante = e;
		 this.inscripcion = new Date();
		 this.seGraduo = false;
	 }
}
