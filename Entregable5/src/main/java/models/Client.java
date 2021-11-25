package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

/**
 * Entidad que va a representar al cliente y a su tabla
 */
@Entity
public class Client {

	@Id @GeneratedValue(strategy = GenerationType.AUTO) 
	@ApiModelProperty(notes = "Id del cliente", required = true)
	private Long id;
	@Column 
	@ApiModelProperty(notes = "Nombre del cliente", required = true)
	private String name;
	@Column 
	@ApiModelProperty(notes = "Apellido del cliente", required = true)
	private String surname;
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
//    private List<Ticket> tickets;
	
	public Client() {}

	/**
	 * 
	 * @param name es el nombre del cliente
	 * @param surname es el apellido del cliente
	 */
	public Client(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	/**
	 * 
	 * @return el nombre del cliente
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name es el nuevo nombre del cliente
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return el apellido del cliente
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * 
	 * @param surname es el nuevo apellido del cliente
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

//	public List<Ticket> getTickets() {
//		return tickets;
//	}
//
//	public void setTickets(List<Ticket> tickets) {
//		this.tickets = tickets;
//	}

	/**
	 * 
	 * @return el id del cliente
	 */
	public Long getId() {
		return id;
	}
	
	public String toString() {
		return this.name;
	}
}
