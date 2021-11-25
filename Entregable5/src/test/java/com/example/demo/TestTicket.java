package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
	
	List<Client> clientList = new ArrayList<Client>();
	private Client fede;
	private Client evy;
	private Product alfajor;
	private Product chocolate;

	@BeforeEach
	public void beforeTest() {
		newClient();
		newProduct();
	}
	
	public void newClient() {
		this.fede = new Client("Federico", "de Muguruza");
		this.evy = new Client("Evelyn", "Vega");
		this.clientRepository.save(fede);
		this.clientRepository.save(evy);
		this.clientList.add(fede);
		this.clientList.add(evy);
	}

	public void newProduct() {
		this.alfajor = new Product("Alfajor", 50, 12);
		this.chocolate = new Product("Chocolate", 120, 10);
		this.productRepository.save(alfajor);
		this.productRepository.save(chocolate);
	}
	
	public Ticket newTicket(Client client) {
		Ticket ticket = new Ticket(client);
		this.ticketRepository.save(ticket);
		return ticket;
	}
	
	public ProductTicket newProductTicket(Ticket ticket, Product product) {
		Random rand = new Random();
		ProductTicket pt = new ProductTicket(ticket, product, rand.nextInt(10+1));
		this.productTicketRepository.save(pt);
		return pt;
	}
	
	@Test
	public void getClientsReport() {
		int i = 0;
		
		for(Client client : this.clientList) {
			
			Ticket ticket = newTicket(client);
			
			ProductTicket pt = newProductTicket(ticket, this.alfajor);
			ProductTicket pt2 = newProductTicket(ticket, this.chocolate);
			
			List<ClientReport> clientReportList = this.ticketRepository.getClientsReport();
			double totalAmount = pt.getQuantity() * pt.getProduct().getPrice() + pt2.getQuantity() * pt2.getProduct().getPrice();
			
			Optional<Client> c = this.clientRepository.findById(client.getId());
			if (c.isPresent()) {
				assertTrue(c.get().getName().equals(client.getName()));
				assertTrue(c.get().getSurname().equals(client.getSurname()));
				assertTrue(totalAmount == clientReportList.get(i).getAmount());
			}
			i++;
		}
	}
	
	@Test
	public void getSalesPerDay() {
		Date created_at = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(created_at);
		calendar.add(Calendar.MONTH, 1);
		String parsedCreatedAt = calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
		
		Ticket ticket = newTicket(this.fede);
		
		ProductTicket pt = newProductTicket(ticket, this.alfajor);
		ProductTicket pt2 = newProductTicket(ticket, this.chocolate);
		
		List<SalesPerDay> salesPerDayList = this.ticketRepository.getSalesPerDay();
     	
		int totalQuantity = pt.getQuantity() + pt2.getQuantity();
		double totalAmount = (pt.getQuantity() * pt.getProduct().getPrice()) + (pt2.getQuantity() * pt2.getProduct().getPrice());
		
		assertTrue(parsedCreatedAt.equals(salesPerDayList.get(0).getDate().toString()));
		assertTrue(totalQuantity == salesPerDayList.get(0).getQuantity());
		assertTrue(totalAmount == salesPerDayList.get(0).getAmount());
	}
	
	@Test
	public void getMostSoldProduct() {
		Ticket ticket = newTicket(this.evy);
		
		ProductTicket pt = newProductTicket(ticket, this.alfajor);
		ProductTicket pt2 = newProductTicket(ticket, this.chocolate);
		
		MostSoldProduct msp = this.ticketRepository.getMostSoldProduct(PageRequest.of(0, 1));

		if(pt.getQuantity() > pt2.getQuantity())
			assertTrue(msp.getName().equals(pt.getProduct().getName()));
		else if(pt.getQuantity() < pt2.getQuantity())
			assertTrue(msp.getName().equals(pt2.getProduct().getName()));
		else {
			assertTrue(msp.getName().equals(pt.getProduct().getName()));
		}
	}
}
