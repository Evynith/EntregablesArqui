package entregable.Entregable4.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cliente {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private int DNI;
	@Column
	private String nombre;
	@OneToMany
	private List<Ticket> tickets;
	
	public Cliente() {
		super();
	}

	public Cliente(int DNI, String nombre, List<Ticket> tickets) {
		super();
		this.nombre = nombre;
		this.tickets = tickets;
		this.DNI = DNI;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public int getDNI() {
		return DNI;
	}
	
	
	
	
}
