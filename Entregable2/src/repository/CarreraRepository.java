package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Carrera;
import entidades.Carrera_Estudiante;
import entidades.Estudiante;

public class CarreraRepository extends GenericRepositoryJPA<Carrera> {

	public CarreraRepository() {
		super();
	}
	
//	ya está defnido en GenericRepositoryJPA
//	public void agregarCarrera(Carrera c) {
//		em.persist(c);
//	}
	
	//b) matricular un estudiante en una carrera
	public void matricularEstudiante(Carrera_Estudiante ce) {
		em.persist(ce);
	}
	
	//f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
	public List<Carrera> getCarreraConEstudiantesOrdenadosCant() {
		Query q = this.em.createQuery("SELECT DISTINCT ce.carrera FROM Carrera_Estudiante ce GROUP BY ce HAVING COUNT(ce.estudiante) > 0 ORDER BY COUNT(ce.estudiante)");
		return q.getResultList();
	}
	
	// TODO PREGUNTAR A QUÉ SE REFIERE CON FILTRADO POR CIUDAD
	//g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
	public List<Carrera> getEstudiantesPorCarreraFiltradoCiudad(String carrera, String ciudad) {
		Query q = this.em.createQuery("SELECT ce.estudiante FROM Carrera_Estudiante ce WHERE ce.carrera.nombre LIKE ?1 AND ce.estudiante.ciudad LIKE ?2")
				.setParameter(1, carrera)
				.setParameter(2, ciudad);
		return q.getResultList();
	}
}
