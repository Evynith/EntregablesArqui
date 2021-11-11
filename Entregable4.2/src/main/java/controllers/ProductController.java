package controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.Product;
import repositories.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Qualifier("productRepository")
	@Autowired
	private final ProductRepository repository;
	
	public ProductController(@Qualifier("productRepository") ProductRepository repository) {
		 this.repository = repository;
	}
	
	@PostMapping("")
	public Product newProduct(@RequestBody Product product) {
		return this.repository.save(product);
	}
	
	@GetMapping("")
	public Iterable<Product> getAll() {
		return this.repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Product> getProduct(@PathVariable Long id) {
		return this.repository.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) {
		this.repository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public void modifyProduct(@PathVariable Long id, @RequestBody Product product) {
		Optional<Product> p = getProduct(id);
		if (p.isPresent()) {
			p.get().setName(product.getName());
			p.get().setPrice(product.getPrice());
			p.get().setStock(product.getStock());
			this.repository.save(p.get());
		}
	}
}
