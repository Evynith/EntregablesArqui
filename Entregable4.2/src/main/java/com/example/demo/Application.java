package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("controllers")
@EntityScan("models")
@EnableJpaRepositories("repositories")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
//	@Bean
//	CommandLineRunner clientRunner(ClientController clientController) {
//		return args -> {
//			// read json and write to db
//			ObjectMapper mapper = new ObjectMapper();
//			TypeReference<List<Client>> typeReference = new TypeReference<List<Client>>(){};
//			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/clients.json");
//			try {
//				List<Client> clients = mapper.readValue(inputStream,typeReference);
//				for(Client c : clients) {
//					clientController.newClient(c);
//					System.out.println("Client Saved!");					
//				}
//			} catch (IOException e){
//				System.out.println("Unable to save clients: " + e.getMessage());
//			}
//		};
//	}
//	
//	@Bean
//	CommandLineRunner productRunner(ProductController productController) {
//		return args -> {
//			// read json and write to db
//			ObjectMapper mapper = new ObjectMapper();
//			TypeReference<List<Product>> typeReference = new TypeReference<List<Product>>(){};
//			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/products.json");
//			try {
//				List<Product> products = mapper.readValue(inputStream,typeReference);
//				for(Product p : products) {
//					productController.newProduct(p);
//					System.out.println("Product Saved!");					
//				}
//			} catch (IOException e){
//				System.out.println("Unable to save products: " + e.getMessage());
//			}
//		};
//	}
//	
//	@Bean
//	CommandLineRunner ticketRunner(TicketController ticketController) {
//		return args -> {
//			// read json and write to db
//			ObjectMapper mapper = new ObjectMapper();
//			TypeReference<List<Ticket>> typeReference = new TypeReference<List<Ticket>>(){};
//			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/tickets.json");
//			try {
//				List<Ticket> tickets = mapper.readValue(inputStream,typeReference);
//				for(Ticket t : tickets) {
//					ticketController.newTicket(t);
//					System.out.println("Ticket Saved!");					
//				}
//			} catch (IOException e){
//				System.out.println("Unable to save tickets: " + e.getMessage());
//			}
//		};
//	}
	
	// TODO arreglar este método que no anda
//	@Bean
//	CommandLineRunner productTicketRunner(ProductTicketController productTicket) {
//		return args -> {
//			// read json and write to db
//			ObjectMapper mapper = new ObjectMapper();
//			TypeReference<List<ProductTicket>> typeReference = new TypeReference<List<ProductTicket>>(){};
//			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/productsTickets.json");
//			try {
//				List<ProductTicket> productsTicket = mapper.readValue(inputStream,typeReference);
//				for(ProductTicket prt : productsTicket) {
//					productTicket.newProductTicket(prt);
//					System.out.println("Product Ticket Saved!");					
//				}
//			} catch (IOException e){
//				System.out.println("Unable to save Products tickets: " + e.getMessage());
//			}
//		};
//	}
}
