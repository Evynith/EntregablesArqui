package repository;

import java.util.List;

import javax.persistence.Query;

import entidades.Carrera;
import entidades.Carrera_Estudiante;
import entidades.Estudiante;

public class CarreraRepository extends GenericRepositoryJPA<Carrera> {

	public CarreraRepository() {
		super();
	}
	
//	ya est� defnido en GenericRepositoryJPA
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
	
	// TODO PREGUNTAR A QU� SE REFIERE CON FILTRADO POR CIUDAD
	//g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
	public List<Carrera> getEstudiantesPorCarreraFiltradoCiudad(String carrera, String ciudad) {
		Query q = this.em.createQuery("SELECT ce.estudiante FROM Carrera_Estudiante ce WHERE ce.carrera.nombre LIKE ?1 AND ce.estudiante.ciudad LIKE ?2");
				q.setParameter(1, carrera);
				q.setParameter(2, ciudad);
		return q.getResultList();
	}
	
	public void graduar(Estudiante e, Carrera c) {
		Query q = this.em.createQuery("UPDATE Carrera_Estudiante SET seGraduo = true, finalizacion = NOW() WHERE estudiante_dni = ?1 AND carrera_id = ?2");
				q.setParameter(1, e.getDni());
				q.setParameter(2, c.getId());
				q.executeUpdate();
	}
	
	public void reporte() {
		Query q = em.createQuery("SELECT new app.Reporte(ce.carrera.nombre, ce.estudiante.nombre, ce.seGraduo, ce.inscripcion)"
		+ " FROM Carrera_Estudiante ce"
		+ " ORDER BY ce.inscripcion, ce.carrera.nombre");
		System.out.println(q.getResultList());
	}
}
