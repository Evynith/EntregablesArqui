package repository;

public abstract class FabricaRepository {

	public static final int DERBY_JDBC = 1;
	public static final int DOCKER_JDBC = 2;
	public static final int MYSQL_JDBC = 3;
	
	public FabricaRepository() {}
	
	public abstract CarreraRepository getCarreraRepository();
	public abstract EstudianteRepository getEstudianteRepository();
	
	/*
	 * crea y retorna una instancia DAO correspondiente 
	 * @param tipo es el nro de persistencia a utilizar (según las constantes)
	 */
	public static FabricaRepository nuevo(int tipo) {
		switch (tipo) {
		case MYSQL_JDBC : return new FabricaJPARepository(); 
		default : return null;
		}
	}
}
