package repository;

import java.util.List;

import javax.ws.rs.core.Response;

import entidades.Estudiante;

public interface EstudianteRepository{
	
	Response save(Estudiante e);
	List<Estudiante> getAll();
	Estudiante getEstudiantePorLibreta(int libreta);
	List<Estudiante> getEstudiantesPorGenero(String genero);
}
