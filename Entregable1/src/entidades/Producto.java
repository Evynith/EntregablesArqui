package entidades;

public class Producto {

	private int id;
	private String nombre;
	private float valor;
	private int cantidad;
	private int idFactura;
	
	public Producto(int id, String nombre, float valor, int cantidad, int idFactura) {
		this.id = id;
		this.nombre = nombre;
		this.valor = valor;
		this.cantidad = cantidad;
		this.idFactura = idFactura;
	}
	public Producto(int id, String nombre, float valor) {
		this.id = id;
		this.nombre = nombre;
		this.valor = valor;
		
	}
	public int getIdFactura() {
		return this.idFactura;
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
