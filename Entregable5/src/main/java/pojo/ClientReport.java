package pojo;

import io.swagger.annotations.ApiModelProperty;

public class ClientReport {

	@ApiModelProperty(notes = "Nombre del cliente", required = true)
	private String name;
	@ApiModelProperty(notes = "Apellido del cliente", required = true)
	private String surname;
	@ApiModelProperty(notes = "Monto total gastado del cliente", required = true)
	private double amount;
	
	public ClientReport() {}
	
	public ClientReport(String name, String surname, double amount) {
		this.name = name;
		this.surname = surname;
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public double getAmount() {
		return amount;
	}
}
