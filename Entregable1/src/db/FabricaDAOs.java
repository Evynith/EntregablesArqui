package db;

public abstract class FabricaDAOs {

	public static final int DERBY_JDBC = 1;
	public static final int DOCKER_JDBC = 2;
	
	public FabricaDAOs() {
		
	}
	
	public abstract ClienteDAO getClienteDAO();
	
	public abstract FacturaDAO getFacturaDAO();
	
	public abstract ProductoDAO getProductoDAO();
	
	/*
	 * crea y retorna una instancia DAO correspondiente 
	 * @param tipo es el nro de persistencia a utilizar (según las constantes)
	 */
	public static FabricaDAOs nuevo(int tipo) {
		switch (tipo) {
		case DERBY_JDBC : return new FabricaDerbyDAO();
		case DOCKER_JDBC : return new FabricaDockerDAO(); 
		default : return null;
		}
	}
	
}
