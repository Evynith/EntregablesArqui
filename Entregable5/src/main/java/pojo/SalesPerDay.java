package pojo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * DTO que almacenará las ventas por día
 */
public class SalesPerDay {

	@ApiModelProperty(notes = "Fecha del día", required = true)
	private Date date;
	@ApiModelProperty(notes = "Cantidad de productos vendidos por día", required = true)
	private Long quantity;
	@ApiModelProperty(notes = "Monto total ganado por día", required = true)
	private double amount;
	
	public SalesPerDay() {}
	
	/**
	 * 
	 * @param date es la fecha del día
	 * @param quantity es la cantidad de productos vendidos por día
	 * @param amount es el monto total ganado por día
	 */
	public SalesPerDay(Date date, Long quantity, double amount) {
		this.date = date;
		this.quantity = quantity;
		this.amount = amount;
	}

	/**
	 * 
	 * @return la fecha del día
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * 
	 * @return la cantidad de productos vendidos por día
	 */
	public Long getQuantity() {
		return quantity;
	}

	/**
	 * 
	 * @return el monto ganado por día
	 */
	public double getAmount() {
		return amount;
	}
}
