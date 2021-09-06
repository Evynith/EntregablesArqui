package app;

import java.sql.SQLException;
import java.util.ArrayList;

import db.FabricaDAOs;
import db.ClienteDAO;
import entidades.Cliente;

public class main {

	public static void main(String[] args) throws SQLException {
		// creo mi fabrica de fabricas de DAO
		FabricaDAOs dockerfactory = FabricaDAOs.nuevo(FabricaDAOs.DOCKER_JDBC);
		ClienteDAO clientDAO = dockerfactory.getClienteDAO();
		//facturaDao
		//ProductoDAO
		
		//---------------de ejercicios del tp normal---------------
//		//creo una nueva persona y la inserto
//		Persona p = new Persona(7, "nombreee", 20);
//		personDAO.insert(p);
//		
		//buscar una persona
		//TODO Persona pers = personDAO.find(1);
		
		//traer personas
//		ArrayList<Cliente> rs = personDAO.list();
//		
//		for(Cliente ps : rs) {
//			System.out.println(ps);
//		}
//		
//		//creo una nueva persona y la inserto
//				Persona p2 = new Persona(8, "nombreee", 20);
//				personDAO.insert(p2);

	}

}
