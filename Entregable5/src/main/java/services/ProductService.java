package services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.swagger.annotations.Api;
import models.Client;
import models.Product;
import repositories.ProductRepository;

@Service
@Api(value = "ProductService", description = "Servicio para la entidad producto")
public class ProductService {
	
	@Qualifier("productRepository")
	@Autowired
	private final ProductRepository productRepository;
	
	public ProductService(@Qualifier("productRepository") ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Transactional
	public boolean actualizaStockIngreso(Product p) {
//		p.setStock(p.getStock() + p.getQuantity());
		Optional<Product> p1 = this.productRepository.findById(p.getId());
		if (p1.isPresent()) {
			p1.get().setStock(p1.get().getStock() + p.getQuantity());
			this.productRepository.save(p1.get());
			return true;
		} else {
			return false;
		}
	}
	
	@Transactional
	public boolean actualizaStockEgreso(Product p) {
//		if (p.getStock() >= p.getQuantity()) {
//			p.setStock(p.getStock() - p.getQuantity());
//			this.productRepository.flush();
		Optional<Product> p1 = this.productRepository.findById(p.getId());
		if (p1.isPresent()) {
			if (p1.get().getStock()  >= p.getQuantity()) {
				p1.get().setStock(p1.get().getStock() - p.getQuantity());
				this.productRepository.save(p1.get());
				return true;
			}
		return true;
		} else {
			return false;
		}
	}
	
	public int getProductDay(Product p, Client c) {
		Integer cantInteger =  this.productRepository.getCantProductOfDay(c, p);
		if(cantInteger != null) {
			return cantInteger;
		} else {
			return 0;
		}
	}


}
