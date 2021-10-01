package repository;

import java.util.List;

import javax.persistence.Query;

import app.CarreraConEstudiantesOrdenadosDesc;
import app.EstudiantesFiltradoCarreraCiudad;
import app.ReporteEstudiante;
import entidades.Carrera;
import entidades.Carrera_Estudiante;
import entidades.Estudiante;

public class CarreraRepository extends GenericRepositoryJPA<Carrera> {

	public CarreraRepository() {
		super();
	}
	
	//b) matricular un estudiante en una carrera
	public void matricularEstudiante(Carrera_Estudiante ce) {
		em.persist(ce);
	}
	
	//f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
	public List<CarreraConEstudiantesOrdenadosDesc> getCarreraConEstudiantesOrdenadosCant() {
		Query q = this.em.createQuery("SELECT DISTINCT new app.CarreraConEstudiantesOrdenadosDesc(ce.carrera.nombre, COUNT(ce.estudiante)) FROM Carrera_Estudiante ce GROUP BY ce.carrera HAVING COUNT(ce.estudiante) > 0 ORDER BY COUNT(ce.estudiante) DESC");
		return q.getResultList();
	}
	
	//g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
	public List<EstudiantesFiltradoCarreraCiudad> getEstudiantesPorCarreraFiltradoCiudad(String carrera, String ciudad) {
		Query q = this.em.createQuery("SELECT new app.EstudiantesFiltradoCarreraCiudad(ce.carrera.nombre, ce.estudiante) FROM Carrera_Estudiante ce WHERE ce.carrera.nombre LIKE ?1 AND ce.estudiante.ciudad LIKE ?2");
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
	
	public List<ReporteEstudiante> reporte() {
		Query q = em.createQuery("SELECT new app.ReporteEstudiante(ce.carrera.nombre, ce.estudiante.nombre, YEAR(ce.inscripcion), YEAR(ce.finalizacion))"
		+ " FROM Carrera_Estudiante ce"
		+ " ORDER BY ce.carrera.nombre, ce.inscripcion, ce.finalizacion");
		return q.getResultList();
	}
}
