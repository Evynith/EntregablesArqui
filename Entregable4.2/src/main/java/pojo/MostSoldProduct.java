package pojo;

public class MostSoldProduct {
	
	private String name;
	private double amount;
	private Long quantity;
	private double pricePerUnit;
	
	public MostSoldProduct() {}
	
	public MostSoldProduct(String name, double amount, Long quantity, double pricePerUnit) {
		this.name = name;
		this.amount = amount;
		this.quantity = quantity;
		this.pricePerUnit = pricePerUnit;
	}

	public String getName() {
		return name;
	}

	public double getAmount() {
		return amount;
	}

	public Long getQuantity() {
		return quantity;
	}
	
	public double getPricePerUnit() {
		return pricePerUnit;
	}
}
