package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;


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
	
	public Product(String name, double price, int stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
	}
	
	public Product(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getQuantity() {
		return this.quantity;
	}

	public Long getId() {
		return id;
	}
	
	public String toString() {
		return this.name;
	}
}
