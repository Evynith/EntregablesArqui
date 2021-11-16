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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import models.Product;
import repositories.ProductRepository;

@RestController
@RequestMapping("/products")
@Api(value = "ProductController", description = "API REST para la entidad producto")
public class ProductController {

	@Qualifier("productRepository")
	@Autowired
	private final ProductRepository repository;
	
	public ProductController(@Qualifier("productRepository") ProductRepository repository) {
		 this.repository = repository;
	}
	
    @ApiOperation(value = "Crea un producto", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 409, message = "Already exists") })
	@PostMapping("")
	public Product newProduct(@RequestBody Product product) {
		return this.repository.save(product);
	}
	
    @ApiOperation(value = "Retorna todos los productos", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
	@GetMapping("")
	public Iterable<Product> getAll() {
		return this.repository.findAll();
	}
	
    @ApiOperation(value = "Retorna un producto por el id", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Invalid id supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
	@GetMapping("/{id}")
	public Optional<Product> getProduct(@PathVariable Long id) {
		return this.repository.findById(id);
	}
	
    @ApiOperation(value = "Elimina un producto por el id", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Invalid id supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) {
		this.repository.deleteById(id);
	}
	
    @ApiOperation(value = "Edita un producto por el id", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Invalid id supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
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
