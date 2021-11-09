package pojo;

public class CartProducts {

	private Long id;
	private String name;
	private int quantity;
	private double amount;
	
	public CartProducts() {}
	
	public CartProducts(Long id, String name, int quantity, double amount) {
		this.name = name;
		this.quantity = quantity;
		this.amount = amount;
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getAmount() {
		return amount;
	}
}
