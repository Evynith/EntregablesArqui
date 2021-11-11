package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String name;
	@Column
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
