//package controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import models.ProductTicket;
//import repositories.ProductTicketRepository;
//
//@RestController
//@RequestMapping("/products-tickets")
//public class ProductTicketController {
//
//	@Qualifier("productsTicketsRepository")
//	@Autowired
//	private final ProductTicketRepository repository;
//	
//	public ProductTicketController(@Qualifier("productsTicketsRepository") ProductTicketRepository repository) {
//		 this.repository = repository;
//	}
//	
//	@PostMapping("")
//	public ProductTicket newProductTicket(@RequestBody ProductTicket prt) {
//		return this.repository.save(prt);
//	}
//	
//}
