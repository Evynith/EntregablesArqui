package services;

import entidades.Carrera;
import entidades.Carrera_Estudiante;
import entidades.Estudiante;
import repository.CarreraMySQL;
import repository.EstudianteMySQL;

public class CarreraService {
	
	public CarreraService() {}
	
	public Carrera_Estudiante matricularEstudiante(int libreta, String carrera){	
		EstudianteMySQL emsql = new EstudianteMySQL();
		CarreraMySQL cmsql = new CarreraMySQL();
		Estudiante e = emsql.getEstudiantePorLibreta(libreta);
		Carrera c = cmsql.getCarrera(carrera);
		if (e != null) {
			Carrera_Estudiante ce = new Carrera_Estudiante(e, c);
			return ce;
		}
		return null;
	}
}


