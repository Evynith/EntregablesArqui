package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

/**
 * Entidad que va a representar al producto y a su tabla
 */
@Entity
public class Product {

	@Id @GeneratedValue(strategy = GenerationType.AUTO) 
	@ApiModelProperty(notes = "Id del producto", required = true)
	private Long id;
	@Column 
	@ApiModelProperty(notes = "Nombre del producto", required = true)
	private String name;
	@Column 
	@ApiModelProperty(notes = "Precio del producto", required = true)
	private double price;
	@Column 
	@ApiModelProperty(notes = "Stock del producto", required = true)
	private int stock;
	@ApiModelProperty(notes = "Cantidad del producto", required = true)
	private transient int quantity;
	
	public Product() {}
	
	/**
	 * 
	 * @param name es el nombre del producto
	 * @param price es el precio del producto
	 * @param stock es el stock del producto
	 */
	public Product(String name, double price, int stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
	}
	
	/**
	 * 
	 * @param quantity es la cantidad que compra el cliente
	 */
	public Product(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * 
	 * @return el nombre del producto
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name el nuevo nombre del producto
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return el precio del producto
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * 
	 * @param price el nuevo precio del producto
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * 
	 * @return el stock del producto
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * 
	 * @param stock es el nuevo stock del producto
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	/**
	 * 
	 * @return la cantidad que compra el cliente
	 */
	public int getQuantity() {
		return this.quantity;
	}

	/**
	 * 
	 * @return el id del producto
	 */
	public Long getId() {
		return id;
	}
	
	public String toString() {
		return this.name;
	}
}
