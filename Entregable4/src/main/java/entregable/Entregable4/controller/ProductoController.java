package entregable.Entregable4.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entregable.Entregable4.entidades.Producto;
import entregable.Entregable4.pojo.ProductReport;
import entregable.Entregable4.servicios.ProductoServicio;
import entregable.Entregable4.servicios.TicketProductoServicio;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired
	private ProductoServicio productoServicio;
	@Autowired
	private TicketProductoServicio ticketProductoServicio;
	
	@GetMapping("")
	public List<Producto> getAll() {
		return this.productoServicio.getProductos();
	}
	
	@PostMapping("")
	public ResponseEntity<Producto> addProducto(@RequestBody Producto c) {
		boolean ok = this.productoServicio.addProducto(c);
		if (!ok) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<Producto>(c, HttpStatus.OK);
	}
	@GetMapping("/mayor")
	public ProductReport getMayorRecaudacion() {
		return this.ticketProductoServicio.getMasVendido();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Producto> putProducto(@PathVariable("id")int id,@RequestBody Producto c) {
		Optional<Producto> viejo = this.productoServicio.getProducto(id);
		if (viejo.isPresent()) {
			if(c.getNombre() != null) viejo.get().setNombre(c.getNombre());
			if(c.getCantidad() != 0) viejo.get().setCantidad(c.getCantidad());
			if(c.getMonto() != 0) viejo.get().setMonto(c.getMonto());
			boolean ok = this.productoServicio.putProducto(viejo.get());
			if (ok) {
				return new ResponseEntity<Producto>(viejo.get(), HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
		
	@DeleteMapping("/{id}")
	public void deleteProducto(@PathVariable("id")int id) {
		this.productoServicio.deleteProducto(id);
	}
}
