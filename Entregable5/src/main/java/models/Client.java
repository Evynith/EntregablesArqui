package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

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

	public Client(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

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

	public Long getId() {
		return id;
	}
	
	public String toString() {
		return this.name;
	}
}
