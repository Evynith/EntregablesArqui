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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import models.Client;
import models.Product;
import models.ProductTicket;
import models.Ticket;
import pojo.ClientProducts;
import pojo.ClientReport;
import pojo.MostSoldProduct;
import pojo.SalesPerDay;
import repositories.ProductTicketRepository;
import repositories.TicketRepository;
import services.ProductService;

@RestController
@RequestMapping("/tickets")
@Api(value = "ProductController", description = "API REST para la entidad ticket")
public class TicketController {

	@Qualifier("ticketRepository")
	@Autowired
	private final TicketRepository repository;
	@Qualifier("productTicketRepository")
	@Autowired
	private final ProductTicketRepository productTicketRepository;
	@Qualifier("productService")
	@Autowired
	private final ProductService productService;
	
	public TicketController(@Qualifier("ticketRepository") TicketRepository repository,
			@Qualifier("productTicketRepository") ProductTicketRepository productTicketRepository,
			@Qualifier("productService") ProductService productService ) {
		 this.repository = repository;
		 this.productTicketRepository = productTicketRepository;
		 this.productService = productService;
	}
	
    @ApiOperation(value = "Crea un ticket", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 409, message = "Already exists") })
	@PostMapping("")
	public void newTicket(@RequestBody ClientProducts cp) {
		Ticket t = new Ticket(cp.getClient());
		this.repository.save(t);		

		for(Product p : cp.getProducts()) {
			ProductTicket pt = new ProductTicket(t, p, p.getQuantity());
			if (3 >= (this.productService.getProductDay(p, t.getClient()) + p.getQuantity() )) {
				this.productService.actualizaStockEgreso(p);
				this.productTicketRepository.save(pt);							
			}
		}
	}
	
    @ApiOperation(value = "Retorna todos los tickets", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
	@GetMapping("")
	public Iterable<Ticket> getAll() {
		return this.repository.findAll();
	}
	
    @ApiOperation(value = "Elimina un ticket por el id", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Invalid id supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
	@DeleteMapping("/{id}")
	public void deleteTicket(@PathVariable Long id) {
		this.repository.deleteById(id);
	}
	
    @ApiOperation(value = "Edita un ticket por el id", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Invalid id supplied"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
	@PutMapping("/{id}")
	public void modifyTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
		this.repository.deleteById(id);
		this.repository.save(ticket);
	}
	
    @ApiOperation(value = "Retorna un reporte de los clientes", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
	@GetMapping("/client-reports")
	public List<ClientReport> getClientsReport() {
		return this.repository.getClientsReport();
	}
	
    @ApiOperation(value = "Retorna un reporte de las ventas diarias", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
	@GetMapping("/sales-per-day")
	public List<SalesPerDay> getSalesPerDay() {
		return this.repository.getSalesPerDay();
	}
	
    @ApiOperation(value = "Retorna un reporte con el producto más vendido", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
	@GetMapping("/most-sold-product")
	public MostSoldProduct getMostSoldProduct() {
		PageRequest limitOne = PageRequest.of(0, 1);
		return this.repository.getMostSoldProduct(limitOne);
	}
}

