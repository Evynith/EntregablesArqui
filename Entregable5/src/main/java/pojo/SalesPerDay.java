package pojo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class SalesPerDay {

	@ApiModelProperty(notes = "Fecha del día", required = true)
	private Date date;
	@ApiModelProperty(notes = "Cantidad de productos vendidos por día", required = true)
	private Long quantity;
	@ApiModelProperty(notes = "Monto total ganado por día", required = true)
	private double amount;
	
	public SalesPerDay() {}
	
	public SalesPerDay(Date date, Long quantity, double amount) {
		this.date = date;
		this.quantity = quantity;
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public Long getQuantity() {
		return quantity;
	}

	public double getAmount() {
		return amount;
	}
}
