package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import entidades.Carrera;
import entidades.Carrera_Estudiante;
import entidades.Estudiante;

public class Main {

	public static void main(String[] args) {
		
		EntityManager em = ManejadorMySQL.conectar();
		
		Carrera tudai = new Carrera("TUDAI");
		Estudiante federico = new Estudiante(942342, "Federico", "de Muguruza", "Masculino", 50, "Tandil");
		Estudiante bruno = new Estudiante(4324234, "Bruno", "Tubino", "Masculino", 40, "Tandil");
		Estudiante evy = new Estudiante(23423423, "Evelin", "Vega", "Femenino", 30, "Tandil");
		Carrera_Estudiante ce = new Carrera_Estudiante(federico, tudai);
		Carrera_Estudiante ce2 = new Carrera_Estudiante(bruno, tudai);
		Carrera_Estudiante ce3 = new Carrera_Estudiante(evy, tudai);
		
		em.persist(tudai);
		em.persist(federico);
		em.persist(bruno);
		em.persist(evy);
		em.persist(ce);
		em.persist(ce2);
		em.persist(ce3);
		
		// c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
		Query q = em.createNamedQuery(federico.OBTENER_TODOS_ORDENADOS);
		System.out.println(q.getResultList());
		
		//d) recuperar un estudiante, en base a su número de libreta universitaria.
		q = em.createQuery("SELECT e FROM Estudiante e WHERE e.libreta = ?1").setParameter(1, 23423423);
		System.out.println(q.getResultList());
		
		 ManejadorMySQL.desconectar();

	}

}
