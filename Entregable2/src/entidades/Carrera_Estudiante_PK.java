package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Carrera_Estudiante_PK implements Serializable {

	@Column(name = "carreras_id")
    private int carrera_id;

    @Column(name = "estudiantes_dni")
    private int estudiante_id;
}
