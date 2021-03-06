package entregable.Entregable4.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Producto{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	@Column
	private String nombre;
	@Column
	private int stock;
	@Column
	private double monto;
	//orphanRemoval = true,  es para poder borrar aunque tenga referenciado
	@OneToMany(mappedBy = "producto") 
	private List<TicketProducto> tickets;

	public Producto() {
		super();
	}

	public Producto(String nombre, int cantidad, double monto) {
		super();
		this.nombre = nombre;
		this.stock = cantidad;
//		this.tickets = new ArrayList<TicketProducto>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return stock;
	}

	public void setCantidad(int cantidad) {
		this.stock = cantidad;
	}

//	public List<TicketProducto> getTickets() {
//		return tickets;
//	}
//
//	public void setTickets(List<TicketProducto> tickets) {
//		this.tickets = tickets;
//	}

	public int getID() {
		return ID;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + "]";
	}
	
	

}
