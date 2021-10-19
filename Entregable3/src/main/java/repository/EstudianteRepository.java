package repository;

import java.util.List;

import javax.ws.rs.core.Response;

import entidades.Estudiante;

public interface EstudianteRepository {
	
	Response save(Estudiante e);
//	Response delete(int id);
	List<Estudiante> getAll();
	Estudiante getEstudiantePorLibreta(int libreta);
	List<Estudiante> getEstudiantePorGenero(String genero);
//	Persona getPersona(int id);
//	List<Persona> getAsignadasTurno();
//	List<Persona> getPersonasPorCiudad(String ciudad);
}
