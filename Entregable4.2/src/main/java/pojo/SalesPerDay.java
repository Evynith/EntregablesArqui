package pojo;

public class SalesPerDay {

	private int day;
	private String name;
	private int quantity;
	private double amount;
//	private List<ProductSale> products = new ArrayList<ProductSale>();
	
	public SalesPerDay() {}
	
	public SalesPerDay(int day, String name, int quantity, double amount) {
		this.day = day;
		this.name = name;
		this.quantity = quantity;
		this.amount = amount;
//		this.products.add(new ProductSale(name, amount, quantity));
	}

	public int getDay() {
		return day;
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

//	public List<ProductSale> getProducts() {
//		return products;
//	}
	
}
