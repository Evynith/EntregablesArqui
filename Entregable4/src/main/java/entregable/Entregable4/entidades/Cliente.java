package entregable.Entregable4.entidades;

import java.io.Serializable;
import java.util.ArrayList;
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
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	private int dni;
	@Column
	private String nombre;
	@OneToMany(mappedBy = "cliente")
	private List<Ticket> tickets;
	
	public Cliente() {
		super();
	}

	public Cliente(int dni, String nombre) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.tickets = new ArrayList<Ticket>();
	}
	
	public Cliente(int dni, String nombre, List<Ticket> tickets) {
		super();
		this.nombre = nombre;
		this.tickets = tickets;
		this.dni = dni;
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

	public int getDni() {
		return dni;
	}
	
	public int getId() {
		return id;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + "]";
	}
	
	
}
