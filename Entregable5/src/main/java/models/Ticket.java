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

/**
 * Entidad que va a representar al ticket y a su tabla
 */
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
	
	/**
	 * 
	 * @param client es el cliente del ticket
	 */
	public Ticket(Client client) {
		this.client = client;
	}

	/**
	 * 
	 * @return el cliente
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * 
	 * @param client es el nuevo cliente
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * 
	 * @return el id del ticket
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @return la fecha de creación del ticket
	 */
	public Date getCreated_at() {
		return created_at;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", client=" + client + ", created_at=" + created_at + "]";
	}
}
