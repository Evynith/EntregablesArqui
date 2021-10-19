package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entidades.Estudiante;
import repository.EstudianteMySQL;
import services.EstudianteService;


@Path("/estudiantes")
public class EstudianteREST {

	private EstudianteMySQL msql = new EstudianteMySQL();
	private EstudianteService es = new EstudianteService();
	
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
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response postEstudiante(@FormParam("nombre") String nombre, @FormParam("apellido") String apellido,
			@FormParam("dni") int dni, @FormParam("libreta") int libreta, @FormParam("genero") String genero,
			@FormParam("edad") int edad, @FormParam("ciudad") String ciudad){
		Response r = this.es.validarDatos(nombre, apellido, dni, libreta, genero, edad, ciudad);
		if (r.getStatus() == 200) {
			Estudiante e = new Estudiante(dni, libreta, nombre, apellido, genero, edad, ciudad);
			return msql.save(e);			
		}
		return r;
	}
	
	// recuperar un estudiante, en base a su número de libreta universitaria.
	@POST
	@Path("/libreta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Estudiante getEstudiantePorLibreta(@FormParam("libreta") int libreta){	
		return msql.getEstudiantePorLibreta(libreta);
	}
	
	// e) recuperar todos los estudiantes, en base a su género.
	@POST
	@Path("/genero")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<Estudiante> getEstudiantesPorGenero(@FormParam("genero") String genero){	
		return msql.getEstudiantesPorGenero(genero);
	}
}
