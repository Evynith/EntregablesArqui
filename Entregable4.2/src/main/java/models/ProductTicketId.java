package models;

import java.io.Serializable;

public class ProductTicketId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Product product;
	private Ticket ticket;
	
	public ProductTicketId() {}
	
	public ProductTicketId(Product product, Ticket ticket) {
		this.product = product;
		this.ticket = ticket;
	}

	public Product getProduct() {
		return product;
	}

	public Ticket getTicket() {
		return ticket;
	}
}

