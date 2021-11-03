package entregable.Entregable4.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import entregable.Entregable4.entidades.Producto;
import entregable.Entregable4.entidades.Ticket;
import entregable.Entregable4.entidades.TicketProducto;
import entregable.Entregable4.servicios.ProductoServicio;
import entregable.Entregable4.servicios.TicketProductoServicio;
import entregable.Entregable4.servicios.TicketServicio;

@RestController
@RequestMapping("/ticket")
public class TIcketProductoController {

	@Autowired
	private TicketServicio ticketServicio;
	@Autowired
	private ProductoServicio productoServicio;
	@Autowired
	private TicketProductoServicio ticketProductoServicio;
	
	@PostMapping("/{id}")
	public ResponseEntity<Ticket> addProducto(@PathVariable("id")int idTicket, @RequestBody TicketProducto tp){
		if (tp != null) {
			
			Optional<Ticket> op = this.ticketServicio.getTicketById(idTicket);
			if (op.isPresent()) {
				Optional<Producto> prod= this.productoServicio.getProducto(tp.getIdProduct());
				if (prod.isPresent()) {
					//restriccion de ´roductos ´pr dia
					int cantidadRestante = this.ticketProductoServicio.getCantidadRestante(op.get().getCliente().getId(),prod.get().getID(), 3, op.get().getFechaEmision());
					//control stock
					if (cantidadRestante > 0 && tp.getCantidadProducto() <= cantidadRestante) {
						tp.setProducto(prod.get());
						tp.setTicket(op.get());
						List<TicketProducto> tt = op.get().getProductos();
						tt.add(tp);
						op.get().setProductos(tt);
						//manejo de stock
						boolean ok = this.ticketProductoServicio.add(tp);
						if (ok) {
						return new ResponseEntity<Ticket>(op.get(), HttpStatus.OK);
//							return new ResponseStatusException(HttpStatus.OK, "Se ha realizado la acción con éxito");
						}
					} else {
					return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//						return new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Supera la cantidad mínima diaria");
					}
					
				}
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//		return new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Ha habido un error");
	}
	
	@DeleteMapping("/{id}") //recibo un idElement
	public ResponseEntity<Ticket> deleteProducto(@PathVariable("id")int idTicket, @RequestBody TicketProducto tp){
		Optional<Ticket> t = this.ticketServicio.getTicketById(idTicket);
		if (!t.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		this.ticketProductoServicio.delete(tp);
		return new ResponseEntity<Ticket>(t.get(), HttpStatus.OK);
	}
	
	@PutMapping("/{id}") //recibo un idProduct, una cantidad y un idElement
	public ResponseEntity<Ticket> putProducto(@PathVariable("id")int idTicket, @RequestBody TicketProducto tp){
		Optional<Ticket> t = this.ticketServicio.getTicketById(idTicket);
		if (t.isPresent()) {
			Optional<TicketProducto> tpp = this.ticketProductoServicio.getById(tp.getIdElement());
			if (tpp.isPresent()) {
				int cantidadRestante = this.ticketProductoServicio.getCantidadRestante(t.get().getCliente().getId(),tpp.get().getIdProduct(), 3, t.get().getFechaEmision());
				if (cantidadRestante > 0 && tp.getCantidadProducto() <= cantidadRestante) {
					
					if (tp.getCantidadProducto() != 0) tpp.get().setCantidadProducto(tp.getCantidadProducto());
					boolean ok = this.ticketProductoServicio.put(tpp.get(), tp.getCantidadProducto());
					if (ok) {
						return new ResponseEntity<Ticket>(t.get(), HttpStatus.OK);
					}	
				} else {
					//supero la cantidad diaria de cosa por dia
					return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
				}
					
			}
		}	
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
}
