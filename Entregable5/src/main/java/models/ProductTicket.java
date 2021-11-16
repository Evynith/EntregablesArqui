package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class ProductTicket implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "Id", required = true)
	private Long id;
	@ManyToOne @JoinColumn(insertable = true, nullable = false)
	@ApiModelProperty(notes = "Ticket del producto", required = true)
	private Ticket ticket;
	@ManyToOne @JoinColumn(insertable = true, nullable = false)
	@ApiModelProperty(notes = "Producto del ticket", required = true)
	private Product product;  
	@Column
	@ApiModelProperty(notes = "Cantidad del producto", required = true)
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
