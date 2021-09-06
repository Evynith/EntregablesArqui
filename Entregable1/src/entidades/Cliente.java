package entidades;

import java.util.ArrayList;

public class Cliente {
	
	private int id;
	private String nombre;
	private String email;
	private ArrayList<Factura> facturas;
	
	public Cliente(int id, String nombre, String email, ArrayList<Factura> facturas) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.facturas = new ArrayList<Factura>();
		this.facturas.addAll(facturas);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public ArrayList<Factura> getFacturas() {
		return facturas;
	}

	public void addFactura(Factura f) {
		this.facturas.add(f);
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", email=" + email + ", facturas=" + facturas + "]";
	}

	
	
}
