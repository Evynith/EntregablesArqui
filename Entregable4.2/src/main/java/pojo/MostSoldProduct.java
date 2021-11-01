package pojo;

public class MostSoldProduct {
	
	private String name;
	private double amount;
	private Long quantity;
	private double prizePerUnit;
	
	public MostSoldProduct() {}
	
	public MostSoldProduct(String name, double amount, Long quantity, double prizePerUnit) {
		this.name = name;
		this.amount = amount;
		this.quantity = quantity;
		this.prizePerUnit = prizePerUnit;
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
	
	public double getPrizePerUnit() {
		return prizePerUnit;
	}
}
