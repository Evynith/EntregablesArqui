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
	
	/**
	 * 
	 * @param name es el nombre del cliente
	 * @param surname es el apellido del cliente
	 * @param amount es la cantidad total gastado del cliente
	 */
	public ClientReport(String name, String surname, double amount) {
		this.name = name;
		this.surname = surname;
		this.amount = amount;
	}

	/**
	 * 
	 * @return el nombre del cliente
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return el apellido del cliente
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * 
	 * @return el monto total gastado del cliente
	 */
	public double getAmount() {
		return amount;
	}
}
