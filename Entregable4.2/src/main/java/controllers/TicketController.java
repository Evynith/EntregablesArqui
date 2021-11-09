package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.Product;
import models.ProductTicket;
import models.Ticket;
import pojo.ClientProducts;
import pojo.ClientReport;
import pojo.MostSoldProduct;
import pojo.SalesPerDay;
import repositories.ProductTicketRepository;
import repositories.TicketRepository;

@RestController
@RequestMapping("/tickets")
public class TicketController {

	@Qualifier("ticketRepository")
	@Autowired
	private final TicketRepository repository;
	@Qualifier("productTicketRepository")
	@Autowired
	private final ProductTicketRepository productTicketRepository;
	
	public TicketController(@Qualifier("ticketRepository") TicketRepository repository,
			@Qualifier("productTicketRepository") ProductTicketRepository productTicketRepository) {
		 this.repository = repository;
		 this.productTicketRepository = productTicketRepository;
	}
	
	@PostMapping("")
	public void newTicket(@RequestBody ClientProducts cp) {
		Ticket t = new Ticket(cp.getClient());
		this.repository.save(t);		

		for(Product p : cp.getProducts()) {
			ProductTicket pt = new ProductTicket(t, p, p.getQuantity());
			this.productTicketRepository.save(pt);					
		}
	}
	
	@GetMapping("")
	public Iterable<Ticket> getAll() {
		return this.repository.findAll();
	}
	
	@DeleteMapping("/{id}")
	public void deleteTicket(@PathVariable Long id) {
		this.repository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public void modifyTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
		this.repository.deleteById(id);
		this.repository.save(ticket);
	}
	
	// Genere un reporte donde se indiquen los clientes y el monto total de sus compras.
	@GetMapping("/client-reports")
	public List<ClientReport> getClientsReport() {
		return this.repository.getClientsReport();
	}
	
	// Genere un reporte con las ventas por día.
	@GetMapping("/sales-per-day")
	public List<SalesPerDay> getSalesPerDay() {
		return this.repository.getSalesPerDay();
	}
	
	// Implemente una consulta para saber cuál fue el producto más vendido.
	@GetMapping("/most-sold-product")
	public MostSoldProduct getMostSoldProduct() {
		PageRequest limitOne = PageRequest.of(0, 1);
		return this.repository.getMostSoldProduct(limitOne);
	}
}

