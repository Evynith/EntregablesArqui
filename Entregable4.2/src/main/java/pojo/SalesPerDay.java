package pojo;

import java.util.ArrayList;
import java.util.List;

public class SalesPerDay {

	private int day;
	private List<ProductSale> products = new ArrayList<ProductSale>();
	
	public SalesPerDay() {}
	
	public SalesPerDay(int day, String name, int quantity, double amount) {
		this.day = day;
		this.products.add(new ProductSale(name, amount, quantity));
	}

	public List<ProductSale> getProducts() {
		return products;
	}
	
	public int getDay() {
		return day;
	}
}
