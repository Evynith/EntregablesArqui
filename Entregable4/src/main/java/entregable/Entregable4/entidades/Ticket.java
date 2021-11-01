package entregable.Entregable4.entidades;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.factory.annotation.Autowired;

import com.sun.source.util.TreePathScanner;

import entregable.Entregable4.repositorios.RepositorioCliente;
import entregable.Entregable4.servicios.ClienteServicio;

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
	@ManyToOne
	@Cascade(CascadeType.PERSIST)
	private Cliente cliente;
	
	private int idCliente;
	
	public Ticket() {
		super();
	}
	
	public Ticket(List<TicketProducto> productos, Date fecha) {
		super();
		this.productos = productos;
		this.fechaEmision = fecha;
//		this.productos = new ArrayList<TicketProducto>();
	}
	
	public Ticket(List<TicketProducto> productos, Cliente cliente) {
		super();
		this.productos = productos;
		this.cliente = cliente;
		this.fechaEmision = new Date(System.currentTimeMillis());
	}

	public Ticket(List<TicketProducto> productos, int idCliente) {
		super();
		this.idCliente = idCliente;
		this.productos = productos;
		this.fechaEmision = new Date(System.currentTimeMillis());
//		this.productos = new ArrayList<TicketProducto>();
	}

	
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getIdCliente() {
		return idCliente;
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
