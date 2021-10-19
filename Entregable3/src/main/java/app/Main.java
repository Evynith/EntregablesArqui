package app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.Carrera;
import entidades.Carrera_Estudiante;
import entidades.Estudiante;

public class Main {

	public static void main(String[] args) throws ParseException {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		int deleted1 = em.createNativeQuery("DELETE FROM carrera_estudiante").executeUpdate();
		int deleted2 = em.createNativeQuery("DELETE FROM carrera").executeUpdate();
		int deleted3 = em.createNativeQuery("DELETE FROM estudiante").executeUpdate();
		
		Estudiante federico = new Estudiante(3424, 942342, "Federico", "de Muguruza", "Masculino", 50, "Azul");
		Estudiante bruno = new Estudiante(77777, 4324234, "Bruno", "Tubino", "Masculino", 40, "Tandil");
		Estudiante evy = new Estudiante(324234, 23423423, "Evelyn", "Vega", "Femenino", 30, "Tandil");
		Estudiante fulana = new Estudiante(666666, 48832432, "Fulana", "De Tal", "Femenino", 20, "Necochea");
		Estudiante hernan = new Estudiante(43432, 1212, "Hernán", "De Tal", "Masculino", 10, "Pinamar");
		
		Carrera tudai = new Carrera("TUDAI");
		Carrera tupar = new Carrera("TUPAR");
		Carrera biologia = new Carrera("Biología");
		
		/**
		 * Se cargan datos de prueba para ir directamente a probar los getters del rest
		 */
		
		em.persist(federico);
		em.persist(bruno);
		em.persist(evy);
		em.persist(fulana);
		em.persist(hernan);
		
		em.persist(tudai);
		em.persist(tupar);
		em.persist(biologia);
		
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

		em.persist(new Carrera_Estudiante(federico, tudai, formatter.parse("10-Dec-2015")));
		em.persist(new Carrera_Estudiante(bruno, tudai, formatter.parse("7-Jun-2013")));
		em.persist(new Carrera_Estudiante(evy, tudai));
		em.persist(new Carrera_Estudiante(fulana, tupar));
		em.persist(new Carrera_Estudiante(hernan, biologia));

		em.getTransaction().commit();
		em.close();
		emf.close();

	}

}
