package repository;

import java.util.List;

import javax.persistence.Query;

import entidades.Estudiante;

//c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
public class EstudianteRepository extends GenericRepositoryJPA<Estudiante> {

	public EstudianteRepository() {
		super();
	}
	
	//d) recuperar un estudiante, en base a su número de libreta universitaria.
	public Estudiante getEstudiantePorLibreta(int libreta) {
		Query q = this.em.createQuery("SELECT e FROM Estudiante e WHERE e.libreta = ?1").setParameter(1, libreta);
		return (Estudiante) q.getSingleResult();			
	}
	
	//c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
	public List<Estudiante> getEstudiantesOrdenadosLibreta() {
		Query q = this.em.createQuery("SELECT e FROM Estudiante e ORDER BY e.libreta");
		return q.getResultList();
	}
	
	// e) recuperar todos los estudiantes, en base a su género.
	public List<Estudiante> getEstudiantesPorGenero(String genero) {
		Query q = this.em.createQuery("SELECT e FROM Estudiante e WHERE e.genero = ?1").setParameter(1, genero);
		return q.getResultList();	
	}
}
