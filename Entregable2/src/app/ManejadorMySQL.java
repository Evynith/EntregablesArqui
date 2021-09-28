package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManejadorMySQL {
	private static EntityManagerFactory emf = null;
	private static EntityManager em = null;
	
	public ManejadorMySQL() {}
	
	public static EntityManager conectar() {
		if(em == null) {
			emf = Persistence.createEntityManagerFactory("mysql");
			em = emf.createEntityManager();
			em.getTransaction().begin();
		}		
		return em;
	}
	
	public static void desconectar() {
		em.getTransaction().commit();
		em.close();
		emf.close();
		em = null;
		emf = null;
	}
}
