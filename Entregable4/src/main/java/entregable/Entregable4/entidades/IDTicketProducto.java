package entregable.Entregable4.entidades;

import java.io.Serializable;

public class IDTicketProducto implements Serializable{
	private static final long serialVersionUID = 1L;
	private Producto producto;
	private Ticket ticket;


	public IDTicketProducto() {
		super();
	}


	public IDTicketProducto(Producto producto, Ticket ticket) {
		super();
		this.producto = producto;
		this.ticket = ticket;
	}  
	
	
	
}
