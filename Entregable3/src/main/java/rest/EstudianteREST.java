package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entidades.Estudiante;
import repository.EstudianteMySQL;


@Path("/estudiantes")
public class EstudianteREST {

	private EstudianteMySQL msql = new EstudianteMySQL();
	
	public EstudianteREST() {}
	
	// recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estudiante> getAll(){	
		return msql.getAll();
	}
	
	// dar de alta un estudiante
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postEstudiante(Estudiante e){
		return msql.save(e);
	}
	
	// recuperar un estudiante, en base a su número de libreta universitaria.
	@GET
	@Path("/libreta/{libreta}")
	@Produces(MediaType.APPLICATION_JSON)
	public Estudiante getEstudiantePorLibreta(@PathParam("libreta") int libreta){	
		return msql.getEstudiantePorLibreta(libreta);
	}
	
	// e) recuperar todos los estudiantes, en base a su género.
	@GET
	@Path("/genero/{genero}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estudiante> getEstudiantePorGenero(@PathParam("genero") String genero){	
		return msql.getEstudiantePorGenero(genero);
	}
}
