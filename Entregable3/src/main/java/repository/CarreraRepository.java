package repository;

import java.util.List;

import javax.ws.rs.core.Response;

import app.ReporteEstudiante;
import entidades.Carrera;
import entidades.Carrera_Estudiante;
import entidades.Estudiante;

public interface CarreraRepository {

	Response matricularEstudiante(Carrera_Estudiante ce);
	List<Carrera> getAll();
	List<Carrera> getCarreraConEstudiantesOrdenadosCant();
	List<Estudiante> getEstudiantesPorCarreraFiltradoCiudad(String carrera, String ciudad);
	List<ReporteEstudiante> getReporte();
	Carrera getCarrera(String carrera);
	
}
