package pojo;

import java.util.Date;

public class SalesPerDay {

	private Date date;
	private Long quantity;
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
