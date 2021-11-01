package pojo;

public class ProductSale {

	private String name;
	private double amount;
	private int quantity;
	
	public ProductSale() {}
	
	public ProductSale(String name, double amount, int quantity) {
		this.name = name;
		this.amount = amount;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public double getAmount() {
		return amount;
	}

	public int getQuantity() {
		return quantity;
	}
}
