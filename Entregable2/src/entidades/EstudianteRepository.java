package entidades;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

//c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
//@NamedQuery(name = EstudianteRepository.OBTENER_TODOS_ORDENADOS, query = "SELECT e FROM Estudiante e ORDER BY e.carreras")
public class EstudianteRepository {

	//public static final String OBTENER_TODOS_ORDENADOS = "EstudianteRepository.OBTENER_TODOS_ORDENADOS";
	private EntityManager em;
	
	public EstudianteRepository(EntityManager em) {
		this.em = em;
	}
	
	//a) dar de alta un estudiante
	public void agregarEstudiante(Estudiante e) {
		em.persist(e);
	}	
	
	// TODO Refactorizar el casteo
	//d) recuperar un estudiante, en base a su número de libreta universitaria.
	public Estudiante getEstudiantePorLibreta(int libreta) {
		 Query q = this.em.createQuery("SELECT e FROM Estudiante e WHERE e.libreta = ?1").setParameter(1, 23423423);
		 return (Estudiante) q.getSingleResult();
	}
	
	//c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
	public List<Estudiante> getEstudiantesOrdenadosEdad() {
		Query q = this.em.createQuery("SELECT e FROM Estudiante e ORDER BY e.edad");
		return q.getResultList();
	}
	
	// e) recuperar todos los estudiantes, en base a su género.
	public List<Estudiante> getEstudiantesPorGenero(String genero) {
		Query q = this.em.createQuery("SELECT e FROM Estudiante e WHERE e.genero = ?1").setParameter(1, genero);
		return q.getResultList();	
	}
}
