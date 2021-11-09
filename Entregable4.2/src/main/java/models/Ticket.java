package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Ticket {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private Client client;
	@Column
	@Temporal(TemporalType.DATE)
	private Date created_at = new Date();
	
	public Ticket() {}
	
	public Ticket(Client client) {
		this.client = client;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public Date getCreated_at() {
		return created_at;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", client=" + client + ", created_at=" + created_at + "]";
	}
}
