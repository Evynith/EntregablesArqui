package entidades;

public class Factura {

	private int id;
	private int idCliente;
	
	public Factura(int id, int idCliente) {
		this.id = id;
		this.idCliente = idCliente;
	}

	public int getId() {
		return id;
	}

	public int getIdCliente() {
		return this.idCliente;
	}

	@Override
	public String toString() {
		return "Factura [id=" + id +"]";
	}
	
	
}
