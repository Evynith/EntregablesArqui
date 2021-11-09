package pojo;

public class RemoveProduct {

	private int quantity;
	private Long id;
	
	public RemoveProduct() {}
	
	public RemoveProduct(int quantity, Long id) {
		this.quantity = quantity;
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public Long getId() {
		return id;
	}
}
