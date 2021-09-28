package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import entidades.Carrera;
import entidades.CarreraRepository;
import entidades.Carrera_Estudiante;
import entidades.Estudiante;
import entidades.EstudianteRepository;

public class Main {

	public static void main(String[] args) {
		
		EntityManager em = ManejadorMySQL.conectar();
		
		Carrera tudai = new Carrera("TUDAI");
		Carrera tupar = new Carrera("TUPAR");
		Estudiante federico = new Estudiante(3424, 942342, "Federico", "de Muguruza", "Masculino", 50, "Azul");
		Estudiante bruno = new Estudiante(324324, 4324234, "Bruno", "Tubino", "Masculino", 40, "Tandil");
		Estudiante evy = new Estudiante(324234, 23423423, "Evelin", "Vega", "Femenino", 30, "Tandil");
		Estudiante fulana = new Estudiante(34324, 423432432, "Fulana", "Vega", "Femenino", 20, "Necochea");
		Carrera_Estudiante ce = new Carrera_Estudiante(federico, tudai);
		Carrera_Estudiante ce2 = new Carrera_Estudiante(bruno, tudai);
		Carrera_Estudiante ce3 = new Carrera_Estudiante(evy, tudai);
		
		EstudianteRepository er = new EstudianteRepository(em);
		CarreraRepository cr = new CarreraRepository(em);
		
		er.agregarEstudiante(federico);
		er.agregarEstudiante(bruno);
		er.agregarEstudiante(evy);
		er.agregarEstudiante(fulana);
		
		cr.agregarCarrera(tudai);
		cr.agregarCarrera(tupar);
		
		cr.matricularEstudiante(evy, tudai);
		cr.matricularEstudiante(federico, tudai);
		cr.matricularEstudiante(bruno, tudai);
		cr.matricularEstudiante(fulana, tupar);
		
		System.out.println(er.getEstudiantesOrdenadosEdad());
		System.out.println(er.getEstudiantePorLibreta(23423423));
		System.out.println(er.getEstudiantesPorGenero("Masculino"));
		System.out.println(cr.getCarreraConEstudiantesOrdenadosCant());
		System.out.println(cr.getEstudiantesPorCarreraFiltradoCiudad(tudai, "Tandil"));
		
		 ManejadorMySQL.desconectar();

	}

}
