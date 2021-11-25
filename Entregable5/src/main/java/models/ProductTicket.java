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

/**
 * Entidad que va a representar al producto con su respectivo ticket y a su tabla
 */
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
	
	/**
	 * 
	 * @param ticket es el ticket perteneciente al producto
	 * @param product es el producto perteneciente al ticket
	 * @param quantity es la cantidad del producto
	 */
	public ProductTicket(Ticket ticket, Product product, int quantity) {
		this.ticket = ticket;
		this.product = product;
		this.quantity = quantity;
	}

	/**
	 * 
	 * @return la cantidad del producto
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * 
	 * @return el producto
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * 
	 * @return el ticket
	 */
	public Ticket getTicket() {
		return ticket;
	}

	@Override
	public String toString() {
		return "ProductTicket [ticket=" + ticket + ", product=" + product + ", quantity=" + quantity + "]";
	}
}
