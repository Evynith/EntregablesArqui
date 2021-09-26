package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Carrera_Estudiante implements Serializable {

	 @EmbeddedId
	 private Carrera_Estudiante_PK id;
	 @ManyToOne
	 @MapsId("dni")
	 @JoinColumn(name = "estudiantes_dni")
	 private Estudiante estudiante;
	 @ManyToOne
	 @MapsId("id")
	 @JoinColumn(name = "carreras_id")
	 private Carrera carrera;  
	 @Column(nullable = false)
	 private Date inscripcion;
	 @Column(nullable = false)
	 private boolean seGraduo;
	 
	 public Carrera_Estudiante() {
		 this.inscripcion = new Date();
		 this.seGraduo = false;
	 }
}
