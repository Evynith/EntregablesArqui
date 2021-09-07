package entidades;

public class ProductoMayorRecaudado {

	private int id;
	private String nombre;
	
	public ProductoMayorRecaudado() {}
	
	public ProductoMayorRecaudado(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "ProductoMayorRecaudado [id=" + id + ", nombre=" + nombre + "]";
	}
}
