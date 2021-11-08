package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Cart {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne
	private Ticket ticket;
	
	public Cart() {}
	
	public Cart(Ticket ticket) {
		this.ticket = ticket;
	}

	public Long getId() {
		return id;
	}

	public Ticket getTicket() {
		return ticket;
	}
}
