package com.example.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import controllers.ClientController;
import controllers.ProductController;
import controllers.TicketController;
import models.Client;
import models.Product;
import pojo.ClientProducts;

@SpringBootApplication
@ComponentScan("controllers")
@EntityScan("models")
@EnableJpaRepositories("repositories")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	CommandLineRunner ticketRunner(TicketController tc, ClientController cc, ProductController pc) {
		return args -> {
			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Client>> typeReferenceClient = new TypeReference<List<Client>>(){};
			TypeReference<List<Product>> typeReferenceProducts = new TypeReference<List<Product>>(){};
			InputStream inputStreamClients = TypeReference.class.getResourceAsStream("/json/clients.json");
			InputStream inputStreamProducts = TypeReference.class.getResourceAsStream("/json/products.json");
			try {
				List<Client> clients = mapper.readValue(inputStreamClients,typeReferenceClient);
				List<Product> products = mapper.readValue(inputStreamProducts,typeReferenceProducts);
				for(Product p : products) {
					pc.newProduct(p);
					System.out.println("Product Saved!");				
				}
			
				for(Client c : clients) {
					List<Product> randomProducts = new ArrayList<Product>();
					cc.newClient(c);
					System.out.println("Client Saved!");
					
					Random rand = new Random();
					for(int i = 0; i < rand.nextInt(products.size()); i++) {
						Product randomProduct = getRandomElement(products);
						randomProducts.add(randomProduct);						
					}
					
					ClientProducts cp = new ClientProducts(c, randomProducts);
					tc.newTicket(cp);
					System.out.println("Ticket Saved!");																								
				}
				
			} catch (IOException e){
				System.out.println("Unable to save tickets: " + e.getMessage());
			}
		};
	}
	
	public Product getRandomElement(List<Product> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
}
