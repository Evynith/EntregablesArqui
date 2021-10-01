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
import repository.CarreraRepository;
import repository.EstudianteRepository;

public class Main {

	public static void main(String[] args) throws ParseException {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		EstudianteRepository er = new EstudianteRepository();
		CarreraRepository cr = new CarreraRepository();
		er.setEntityManager(em);
		cr.setEntityManager(em);
		
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
		
        er.agregar(federico);
        er.agregar(bruno);
        er.agregar(evy);
        er.agregar(fulana); 
        er.agregar(hernan);
        
        cr.agregar(tudai);
        cr.agregar(tupar);
        cr.agregar(biologia);
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

		cr.matricularEstudiante(new Carrera_Estudiante(federico, tudai, formatter.parse("10-Dec-2015")));
		cr.matricularEstudiante(new Carrera_Estudiante(bruno, tudai, formatter.parse("7-Jun-2013")));
		cr.matricularEstudiante(new Carrera_Estudiante(evy, tudai));
		cr.matricularEstudiante(new Carrera_Estudiante(fulana, tupar));
		cr.matricularEstudiante(new Carrera_Estudiante(hernan, biologia));
		
		cr.graduar(federico, tudai);
		
		System.out.println("Estudiantes ordenados por libreta: ");
		System.out.println(er.getEstudiantesOrdenadosLibreta());
		System.out.println("Estudiante por libreta: ");
		System.out.println(er.getEstudiantePorLibreta(23423423));
		System.out.println("Estudiantes por género: ");
		System.out.println(er.getEstudiantesPorGenero("Femenino"));
		System.out.println("Carreras ordenadas por cantidad de estudiantes: ");
		System.out.println(cr.getCarreraConEstudiantesOrdenadosCant());
		System.out.println("Estudiantes de una carrera y ciudad determinadas: ");
		System.out.println(cr.getEstudiantesPorCarreraFiltradoCiudad("tudai", "Tandil"));
		System.out.println("Reporte de carreras:");
		System.out.println(cr.reporte());

		em.getTransaction().commit();
		em.close();
		emf.close();
	}

}
