package com.example.demo;

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
	public void getProduct() {
		Product newProduct = new Product("Alfajor", 50, 12);
		
		this.repository.save(newProduct);
		
		Optional<Product> p = this.repository.findById(newProduct.getId());
		if (p.isPresent())
			assertTrue(p.get() != null);
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