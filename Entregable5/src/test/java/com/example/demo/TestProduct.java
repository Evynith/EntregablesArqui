package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import controllers.ProductController;
import models.Product;
import repositories.ProductRepository;

@DataJpaTest
public class TestProduct {
	
	@Autowired
	private ProductRepository repository;
	
	@Test
	public void newProduct() {
		Product newProduct = new Product("Alfajor", 50, 12);
		
		this.repository.save(newProduct);
		
		Optional<Product> product = this.repository.findById(newProduct.getId());
		if (product.isPresent()) 
			assertTrue(product.get().getId() == newProduct.getId());	
	}

	@Test
	public void getProduct() {
		Product newProduct = new Product("Alfajor", 50, 12);
		
		this.repository.save(newProduct);
		
		Optional<Product> p = this.repository.findById(newProduct.getId());
		if (p.isPresent())
			assertTrue(p.get() != null);
	}
	
	@Test
	public void deleteAllProducts() {
		Product newProduct = new Product("Alfajor", 50, 12);
		Product newProduct2 = new Product("Chocolate", 120, 10);
		
		this.repository.save(newProduct);
		this.repository.save(newProduct2);
		
		this.repository.deleteAll();
		
		assertThat(repository.findAll()).isEmpty();
	}
	
	@Test
	public void deleteProductById() {
		Product newProduct = new Product("Alfajor", 50, 12);
		
		this.repository.save(newProduct);
		
		Optional<Product> p = this.repository.findById(newProduct.getId());
		if (p.isPresent())
			this.repository.deleteById(p.get().getId());
	}
	
	@Test
	public void modifyProduct() {
		ProductController pc = new ProductController(this.repository);
		Product newProduct = new Product("Alfajor", 50, 12);
		Product newInfoProduct = new Product("Caramelo", 5, 100);
		
		this.repository.save(newProduct);
		
		pc.modifyProduct(newProduct.getId(), newInfoProduct);
		
		assertTrue(newProduct.getName().equals(newInfoProduct.getName()));
	}
	
}