package rest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class EMF implements ServletContextListener {
	
	private static EntityManagerFactory EMF;
	
	public static EntityManager createEntityManager() {
		if (EMF == null) 
			throw new IllegalStateException("Context is not initialized yet.");
		System.out.println("createEntityManager");
		return EMF.createEntityManager(); 						
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		EMF = Persistence.createEntityManagerFactory("mysql");
		System.out.println("contextInitialized");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if (EMF != null)
			EMF.close();
	}
}
