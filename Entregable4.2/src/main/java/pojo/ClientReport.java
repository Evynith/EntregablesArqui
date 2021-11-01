package pojo;

public class ClientReport {

	private String name;
	private String surname;
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
