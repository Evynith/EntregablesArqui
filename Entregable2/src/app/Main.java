package app;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Carrera;
import entidades.Estudiante;

public class Main {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();
		
		Carrera tudai = new Carrera("TUDAI");
		Estudiante federico = new Estudiante(942342, "Federico", "de Muguruza", "Masculino", 22, "Tandil");
		federico.agregarCarrera(tudai);
		
		em.getTransaction().begin();	
		em.persist(tudai);
		em.persist(federico);
		em.getTransaction().commit();
		em.close();
		emf.close();

	}

}
