package pojo;

import java.util.List;

import models.Client;
import models.Product;

public class ClientProducts {

	private Client client;
	private List<Product> products;
	
	public ClientProducts() {}
	
	/**
	 **
	 * @param client es el cliente
	 * @param products son los productos del cliente
	 */
	public ClientProducts(Client client, List<Product> products) {
		this.client = client;
		this.products = products;
	}

	/**
	 * 
	 * @return el cliente
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * 
	 * @return los productos del cliente
	 */
	public List<Product> getProducts() {
		return products;
	}
}
