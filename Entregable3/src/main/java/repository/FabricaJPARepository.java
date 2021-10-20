package repository;

public class FabricaJPARepository extends FabricaRepository {

	
	
	public FabricaJPARepository() {}
	
	@Override
	public CarreraRepository getCarreraRepository() {
		return new CarreraMySQL();
	}

	@Override
	public EstudianteRepository getEstudianteRepository() {
		return new EstudianteMySQL();
	}
}
