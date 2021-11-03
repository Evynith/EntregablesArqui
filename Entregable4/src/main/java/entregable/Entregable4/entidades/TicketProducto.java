package entregable.Entregable4.entidades;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

import entregable.Entregable4.servicios.ProductoServicio;

@Entity
@Table(name = "ticket_producto")
//@IdClass(IDTicketProducto.class)
public class TicketProducto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
//	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(insertable = true, nullable = false, name="id_producto")
	private Producto producto;
	
//	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(insertable = true, nullable = false, name= "id_ticket")
	private Ticket ticket;  
	@Column(nullable = false)
	private int cantidadProducto;
	
	private int idProduct;
	private int idElement;
	
	public void setIdElement(int idElement) {
		this.idElement = idElement;
	}

	public int getIdElement() {
		return idElement;
	}

	public TicketProducto(int idProduct , int cantidadProducto, int idElement) {
		super();
		this.idProduct = idProduct;
		this.cantidadProducto = cantidadProducto;
		this.idElement = idElement;
	}

	public TicketProducto(int idElement) {
		super();
		this.idElement = idElement;
	}

	public TicketProducto() {
		super();
		
	}

	public int getID() {
		return ID;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
@JsonIgnore
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
	
	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public TicketProducto(Producto producto, int cantidadProducto) {
		super();
		this.producto = producto;
//		this.ticket = ticket;
		this.cantidadProducto = cantidadProducto;
	}

	public TicketProducto(int idProduct, int cantidadProducto) {
		super();
		this.idProduct = idProduct;
//		this.ticket = ticket;
		this.cantidadProducto = cantidadProducto;
	}

	@Override
	public String toString() {
		return "";
	}
	
	
}

	
	
