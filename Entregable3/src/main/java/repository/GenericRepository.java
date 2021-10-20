package repository;


//https://www.arquitecturajava.com/java-generic-repository-y-jpa/
public interface GenericRepository<T>{
	 
    void agregar(T t);
    void borrar(T t);
    T buscar(T t);
    void actualizar(T t);
    Iterable<T> buscarTodos();
}

