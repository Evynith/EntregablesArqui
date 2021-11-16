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

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Ticket {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "Id del ticket", required = true)
	private Long id;
	@ManyToOne
	@ApiModelProperty(notes = "Cliente del ticket", required = true)
	private Client client;
	@Column @Temporal(TemporalType.DATE)
	@ApiModelProperty(notes = "Fecha de creación del ticket", required = true)
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
