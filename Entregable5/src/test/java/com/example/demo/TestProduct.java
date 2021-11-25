package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
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
	private List<Product> productList = new ArrayList<Product>();
	
	@BeforeEach
	public void beforeTest() {
		Product alfajor = new Product("Alfajor", 50, 12);
		Product leche = new Product("Leche", 145, 15);
		
		this.productList.add(alfajor);
		this.productList.add(leche);
	}
	
	@Test
	public void newProduct() {
		Product alfajor = this.productList.get(0);
		
		this.repository.save(alfajor);
		
		Optional<Product> product = this.repository.findById(alfajor.getId());
		if (product.isPresent()) 
			assertTrue(product.get().getId() == alfajor.getId());	
	}

	@Test
	public void getProduct() {
		Product alfajor = this.productList.get(0);
		
		this.repository.save(alfajor);
		
		Optional<Product> p = this.repository.findById(alfajor.getId());
		if (p.isPresent())
			assertTrue(p.get() != null);
	}
	
	@Test
	public void deleteProductById() {
		Product alfajor = this.productList.get(0);
		
		this.repository.save(alfajor);
		
		Optional<Product> product = this.repository.findById(alfajor.getId());
		if (product.isPresent()) {
			this.repository.deleteById(product.get().getId());
			assertFalse(this.repository.existsById(product.get().getId()));
		}
	}
	
	@Test
	public void modifyProduct() {
		ProductController pc = new ProductController(this.repository);
		Product alfajor = this.productList.get(0);
		Product leche = this.productList.get(1);
		
		this.repository.save(alfajor);
		
		pc.modifyProduct(alfajor.getId(), leche);
		
		assertTrue(alfajor.getName().equals(leche.getName()));
	}
	
}