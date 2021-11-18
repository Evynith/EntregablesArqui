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
	
	/**
	 * 
	 * @param name el nombre del producto
	 * @param amount el monto total ganado del producto
	 * @param quantity cantidad del producto vendido
	 * @param pricePerUnit precio por unidad del producto
	 */
	public MostSoldProduct(String name, double amount, Long quantity, double pricePerUnit) {
		this.name = name;
		this.amount = amount;
		this.quantity = quantity;
		this.pricePerUnit = pricePerUnit;
	}

	/**
	 * 
	 * @return el nombre del producto
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return el monto total ganado del producto
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * 
	 * @return la cantidad total vendida del producto
	 */
	public Long getQuantity() {
		return quantity;
	}
	
	/**
	 * 
	 * @return el precio por unidad del producto
	 */
	public double getPricePerUnit() {
		return pricePerUnit;
	}
}
