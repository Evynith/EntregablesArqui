package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.Carrera;
import entidades.Carrera_Estudiante;
import entidades.Estudiante;

public class Main {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();
		
		Carrera tudai = new Carrera("TUDAI");
		Estudiante federico = new Estudiante(942342, "Federico", "de Muguruza", "Masculino", 22, "Tandil");
		Carrera_Estudiante ce = new Carrera_Estudiante(federico,tudai);
		
		em.getTransaction().begin();	
		em.persist(tudai);
		em.persist(federico);
		em.persist(ce);
		em.getTransaction().commit();
		em.close();
		emf.close();

	}

}
