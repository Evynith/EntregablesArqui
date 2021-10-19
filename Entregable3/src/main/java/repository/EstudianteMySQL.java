package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.ws.rs.core.Response;

import entidades.Estudiante;
import rest.EMF;

public class EstudianteMySQL implements EstudianteRepository {

	@Override
	public List<Estudiante> getAll() {
		EntityManager em = EMF.createEntityManager();
		Query q = em.createQuery("SELECT e.nombre, e.apellido, e.edad, e.genero"
				+ " FROM Estudiante e"
				+ " ORDER BY e.edad");
		return q.getResultList();
	}
	
	@Override
	public Response save(Estudiante e) {
		EntityManager em = EMF.createEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(e);
			em.getTransaction().commit();
			return Response.status(201).build();			
		} catch (Exception exc) {
			return Response.status(500).build();	
		}
	}

	@Override
	public Estudiante getEstudiantePorLibreta(int libreta) {
		EntityManager em = EMF.createEntityManager();
		Estudiante estudiante = null;
		try {			
			Query q = em.createQuery("SELECT e FROM Estudiante e WHERE e.libreta = ?1").setParameter(1, libreta);
			return (Estudiante) q.getSingleResult();	
		} catch (NoResultException e) {
			System.out.println("No se encuentra el estudiante.");
		}
		return estudiante;
	}

	@Override
	public List<Estudiante> getEstudiantePorGenero(String genero) {
		EntityManager em = EMF.createEntityManager();
		Query q = em.createQuery("SELECT e FROM Estudiante e WHERE e.genero = ?1").setParameter(1, genero);
		return q.getResultList();
	}
	
	
}
