package entregable.Entregable4.entidades;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	@Column(nullable = false)
	private Date fechaEmision;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ticket") 
	@Cascade(CascadeType.PERSIST)
	private List<TicketProducto> productos;

	public Ticket() {
		super();
	}
	
	public Ticket(List<TicketProducto> productos, Date fecha) {
		super();
		this.productos = productos;
		this.fechaEmision = fecha;
//		this.productos = new ArrayList<TicketProducto>();
	}
	
	public Ticket(List<TicketProducto> productos) {
		super();
		this.productos = productos;
		this.fechaEmision = new Date(System.currentTimeMillis());
//		this.productos = new ArrayList<TicketProducto>();
	}

	public List<TicketProducto> getProductos() {
		return productos;
	}

	public void setProductos(List<TicketProducto> productos) {
		this.productos = productos;
	}

	public int getID() {
		return ID;
	}
	
	
	

}
