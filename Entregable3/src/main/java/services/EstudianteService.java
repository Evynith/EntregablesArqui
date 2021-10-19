package services;

import javax.ws.rs.core.Response;

import repository.EstudianteMySQL;

public class EstudianteService {
	
	private EstudianteMySQL emsql = new EstudianteMySQL();
	
	public EstudianteService() {}
	
	public Response validarDatos(String nombre, String apellido, int dni, int libreta, String genero, int edad, String ciudad) {
		if (nombre == null || apellido == null || dni == 0 || libreta == 0 || genero == null || edad == 0 || ciudad == null) 
			 return Response.status(500).build();			
		return Response.status(200).build();			
	}
}