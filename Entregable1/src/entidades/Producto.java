package entidades;

public class Producto {

	private int id;
	private String nombre;
	private float valor;
	private int cantidad;
	
	public Producto(int id, String nombre, float valor, int cantidad) {
		this.id = id;
		this.nombre = nombre;
		this.valor = valor;
		this.cantidad = cantidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", valor=" + valor + ", cantidad=" + cantidad + "]";
	}
	
	
	
}
