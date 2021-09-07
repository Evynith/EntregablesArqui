package entidades;

public class ClientePorFacturacionDesc {

	private int id;
	private String nombre;
	private float recaudacion;
	
	
	public ClientePorFacturacionDesc(int id, String nombre, float recaudacion) {
		this.id = id;
		this.nombre = nombre;
		this.recaudacion = recaudacion;
	}

	@Override
	public String toString() {
		return "ClientePorFacturacionDesc [id=" + id + ", nombre=" + nombre + ", recaudación=" + this.recaudacion + "]";
	}
	
}
