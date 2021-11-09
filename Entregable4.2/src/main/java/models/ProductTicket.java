package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProductTicket implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(insertable = true, nullable = false)
	private Ticket ticket;
	@ManyToOne
	@JoinColumn(insertable = true, nullable = false)
	private Product product;  
	@Column
	private int quantity;
	
	public ProductTicket() {}
	
	public ProductTicket(Ticket ticket, Product product, int quantity) {
		this.ticket = ticket;
		this.product = product;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public Product getProduct() {
		return product;
	}

	public Ticket getTicket() {
		return ticket;
	}

	@Override
	public String toString() {
		return "ProductTicket [ticket=" + ticket + ", product=" + product + ", quantity=" + quantity + "]";
	}
}
