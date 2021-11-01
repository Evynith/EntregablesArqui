package entregable.Entregable4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entregable.Entregable4.entidades.Cliente;
import entregable.Entregable4.entidades.Producto;
import entregable.Entregable4.servicios.ClienteServicio;
import entregable.Entregable4.servicios.ProductoServicio;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired
	private ProductoServicio productoServicio;
	
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
}
