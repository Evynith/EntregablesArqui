package pojo;

import io.swagger.annotations.ApiModelProperty;

public class MostSoldProduct {
	
	@ApiModelProperty(notes = "Nombre del producto", required = true)
	private String name;
	@ApiModelProperty(notes = "Monto total ganado del producto", required = true)
	private double amount;
	@ApiModelProperty(notes = "Cantidad del producto vendido", required = true)
	private Long quantity;
	@ApiModelProperty(notes = "Precio por unidad del producto", required = true)
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
