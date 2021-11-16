package pojo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import models.Client;
import models.Product;

public class ClientProducts {

	private Client client;
	@ApiModelProperty(notes = "Productos del cliente", required = true)
	private List<Product> products;
	
	public ClientProducts() {}
	
	public ClientProducts(Client client, List<Product> products) {
		this.client = client;
		this.products = products;
	}

	public Client getClient() {
		return client;
	}

	public List<Product> getProducts() {
		return products;
	}
}
