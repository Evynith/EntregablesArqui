package entregable.Entregable4.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	@Column
	private String nombre;
	@Column
	private int cantidad;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "producto") 
	private List<TicketProducto> tickets;

	public Producto() {
		super();
	}

	public Producto(String nombre, int cantidad, List<TicketProducto> tickets) {
		super();
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.tickets = tickets;
//		this.tickets = new ArrayList<TicketProducto>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public List<TicketProducto> getTickets() {
		return tickets;
	}

	public void setTickets(List<TicketProducto> tickets) {
		this.tickets = tickets;
	}

	public int getID() {
		return ID;
	}
	
	

}
