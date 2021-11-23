package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import models.Client;
import models.Product;
import models.ProductTicket;
import models.Ticket;
import pojo.ClientReport;
import pojo.MostSoldProduct;
import pojo.SalesPerDay;
import repositories.ClientRepository;
import repositories.ProductRepository;
import repositories.ProductTicketRepository;
import repositories.TicketRepository;

@DataJpaTest
public class TestTicket {

	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductTicketRepository productTicketRepository;
	
	private Client client;
	private Client client2;
	private Product product;
	private Product product2;
	private Ticket ticket;
	private Ticket ticket2;
	private ProductTicket pt;
	private ProductTicket pt2;
	private ProductTicket pt3;
	private double totalAmount;

	@BeforeEach
	public void beforeEach() {
		newClient();
		newProduct();
		newTicket();
		newProductTicket();
	}
	
	public void newClient() {
		this.client = new Client("Federico", "de Muguruza");
		this.client2 = new Client("Federico2", "de Muguruza2");
		this.clientRepository.save(client);
		this.clientRepository.save(client2);
	}
	
	public void newProduct() {
		this.product = new Product("Alfajor", 50, 12);
		this.product2 = new Product("Chocolate", 120, 10);
		this.productRepository.save(product);
		this.productRepository.save(product2);
	}
	
	public void newTicket() {
		this.ticket = new Ticket(client);
		this.ticketRepository.save(ticket);
		this.ticket2 = new Ticket(client2);
		this.ticketRepository.save(ticket2);
	}
	
	public void newProductTicket() {
		this.pt = new ProductTicket(ticket, product, 3);
		this.pt2 = new ProductTicket(ticket, product2, 2);
		this.pt3 = new ProductTicket(ticket2, product2, 2);
		this.productTicketRepository.save(pt);
		this.productTicketRepository.save(pt2);
		this.productTicketRepository.save(pt3);
	}
	
	@Test
	public void getClientsReport() {
		List<Client> clients = new ArrayList<Client>();
		clients.add(client);
		clients.add(client2);
		
		int i = 0;
		
		for(Client client : clients) {
			
			List<ClientReport> clientReportList = this.ticketRepository.getClientsReport();
			
			this.totalAmount = pt.getQuantity() * pt.getProduct().getPrice();
			this.totalAmount += pt2.getQuantity() * pt2.getProduct().getPrice();
			
			if (client.getName().equals("Federico2")) {
				this.totalAmount = 0;
				this.totalAmount += pt3.getQuantity() * pt3.getProduct().getPrice();
			}
			
			Optional<Client> c = this.clientRepository.findById(client.getId());
			if (c.isPresent()) {
				assertTrue(c.get().getName().equals(client.getName()));
				assertTrue(c.get().getSurname().equals(client.getSurname()));
				assertTrue(this.totalAmount == clientReportList.get(i).getAmount());
			}
			i++;
		}
	}
	
	@Test
	public void getSalesPerDay() {
		List<SalesPerDay> salesPerDayList = this.ticketRepository.getSalesPerDay();
		
		Date created_at = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(created_at);
		calendar.add(Calendar.MONTH, 1);
		String parsedCreatedAt = calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
     	
		int totalQuantity = pt.getQuantity() + pt2.getQuantity() + pt3.getQuantity();
		double totalAmount = (pt.getQuantity() * pt.getProduct().getPrice()) + (pt2.getQuantity() * pt2.getProduct().getPrice() + (pt3.getQuantity() * pt3.getProduct().getPrice()));
		
		assertTrue(parsedCreatedAt.equals(salesPerDayList.get(0).getDate().toString()));
		assertTrue(totalQuantity == salesPerDayList.get(0).getQuantity());
		assertTrue(totalAmount == salesPerDayList.get(0).getAmount());
	}
	
	@Test
	public void getMostSoldProduct() {
		PageRequest limitOne = PageRequest.of(0, 1);
		MostSoldProduct msp = this.ticketRepository.getMostSoldProduct(limitOne);
		
		if(pt.getQuantity() > pt2.getQuantity())
			assertTrue(msp.getName().equals(pt.getProduct().getName()));
		else if (pt.getQuantity() < pt2.getQuantity())
			assertTrue(msp.getName().equals(pt2.getProduct().getName()));
		else
			assertTrue(msp.getName().equals(pt3.getProduct().getName()));
	}
}
