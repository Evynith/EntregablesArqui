package services;

import entidades.Carrera;
import entidades.Carrera_Estudiante;
import entidades.Estudiante;
import repository.CarreraMySQL;
import repository.EstudianteMySQL;

public class CarreraService {
	
	private EstudianteMySQL emsql = new EstudianteMySQL();
	private CarreraMySQL cmsql = new CarreraMySQL(); 
	
	public CarreraService() {}
	
	public Carrera_Estudiante validarLibreta(int libreta, String carrera){	
		Estudiante e = this.emsql.getEstudiantePorLibreta(libreta);
		Carrera c = this.cmsql.getCarrera(carrera);
		if (e != null) {
			Carrera_Estudiante ce = new Carrera_Estudiante(e, c);
			return ce;
		}
		return null;
	}
}


