package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Product {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String name;
	@Column
	private double prize;
	
	public Product() {}
	
	public Product(String name, double prize) {
		this.name = name;
		this.prize = prize;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public Double getPrize() {
		return this.prize;
	}
}
