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

import app.ReporteEstudiante;
import entidades.Carrera;
import entidades.Carrera_Estudiante;
import entidades.Estudiante;
import entidades.EstudianteAMatricular;
import repository.CarreraMySQL;
import services.CarreraService;

@Path("/carreras")
public class CarreraREST {

	private CarreraMySQL msql = new CarreraMySQL();
	private CarreraService cs = new CarreraService();
	
	public CarreraREST() {}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Carrera> getAll(){	
		return msql.getAll();
	}
	
	// matricular un estudiante en una carrera
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response matricularEstudiante(EstudianteAMatricular eam){		
		Carrera_Estudiante ce = this.cs.validarLibreta(eam.getLibreta(), eam.getCarrera());
		
		if (ce != null) {
			msql.matricularEstudiante(ce);
			return Response.status(201).build();	
		}
		return Response.status(400).build();	
	}
	
	// recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
	@GET
	@Path("/con-estudiantes-ordenados")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Carrera> getCarreraConEstudiantesOrdenadosCant(){	
		return msql.getCarreraConEstudiantesOrdenadosCant();
	}
	
//	// recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
	@GET
	@Path("/carrera-ciudad/{carrera}/{ciudad}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estudiante> getEstudiantesPorCarreraFiltradoCiudad(@PathParam("carrera") String carrera, @PathParam("ciudad") String ciudad){	
		return msql.getEstudiantesPorCarreraFiltradoCiudad(carrera, ciudad);
	}
	
	@GET
	@Path("/reporte")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ReporteEstudiante> reporte() {
		return msql.getReporte();
	}
}
