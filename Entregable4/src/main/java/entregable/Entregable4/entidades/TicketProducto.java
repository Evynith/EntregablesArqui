package entregable.Entregable4.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ticket_producto")
@IdClass(IDTicketProducto.class)
public class TicketProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(insertable = true, nullable = false)
	private Producto producto;
	@Id
	@ManyToOne 
	@JoinColumn(insertable = true, nullable = false)
	private Ticket ticket;  
	@Column(nullable = false)
	private int cantidadProducto;
	
	public TicketProducto() {
		super();
		
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public int getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	public TicketProducto(Producto producto, Ticket ticket, int cantidadProducto) {
		super();
		this.producto = producto;
		this.ticket = ticket;
		this.cantidadProducto = cantidadProducto;
	}
	
	public TicketProducto(Producto producto, int cantidadProducto) {
		super();
		this.producto = producto;
//		this.ticket = ticket;
		this.cantidadProducto = cantidadProducto;
	}
	
	
	
}

	
	
