package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.core.Response;

import app.ReporteEstudiante;
import entidades.Carrera;
import entidades.Carrera_Estudiante;
import entidades.Estudiante;
import rest.EMF;

public class CarreraMySQL implements CarreraRepository {

	@Override
	public List<Carrera> getAll() {
		EntityManager em = EMF.createEntityManager();
		Query q = em.createQuery("SELECT c.nombre"
				+ " FROM Carrera c");
		return q.getResultList();
	}
	
	@Override
	public Response matricularEstudiante(Carrera_Estudiante ce) {
		EntityManager em = EMF.createEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(ce);
			em.getTransaction().commit();
			return Response.status(201).build();			
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
			return Response.status(500).build();	
		}
	}

	@Override
	public List<Carrera> getCarreraConEstudiantesOrdenadosCant() {
		EntityManager em = EMF.createEntityManager();
		Query q = em.createQuery("SELECT DISTINCT new app.CarreraConEstudiantesOrdenadosDesc(ce.carrera.nombre, COUNT(ce.estudiante)) FROM Carrera_Estudiante ce GROUP BY ce.carrera HAVING COUNT(ce.estudiante) > 0 ORDER BY COUNT(ce.estudiante) DESC");
		return q.getResultList();
	}
	
	@Override
	public Carrera getCarrera(String carrera) {
		EntityManager em = EMF.createEntityManager();
		Query q = em.createQuery("SELECT c"
				+ " FROM Carrera"
				+ " WHERE c.nombre LIKE ?1")
				.setParameter(1,  carrera);
		return (Carrera) q.getSingleResult();
	}

	@Override
	public List<Estudiante> getEstudiantesPorCarreraFiltradoCiudad(String carrera, String ciudad) {
		EntityManager em = EMF.createEntityManager();
		Query q = em.createQuery("SELECT new app.EstudiantesFiltradoCarreraCiudad(ce.carrera.nombre, ce.estudiante) FROM Carrera_Estudiante ce WHERE ce.carrera.nombre LIKE ?1 AND ce.estudiante.ciudad LIKE ?2");
		q.setParameter(1, carrera);
		q.setParameter(2, ciudad);
		return q.getResultList();
	}

	@Override
	public List<ReporteEstudiante> getReporte() {
		EntityManager em = EMF.createEntityManager();
		Query q = em.createQuery("SELECT new app.ReporteEstudiante(ce.carrera.nombre, ce.inscripcion, COUNT(ce.seGraduo = false), COUNT(ce.seGraduo = true)"
				+ " FROM Carrera_Estudiante ce"
				+ " GROUP BY YEAR(ce.inscripcion)");
				return q.getResultList();
	}
	
}
