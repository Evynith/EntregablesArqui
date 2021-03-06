package entregable.Entregable4.entidades;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;


@Entity
public class Ticket{

	public Date getFechaEmision() {
		return fechaEmision;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	@Column(nullable = false)
	private Date fechaEmision;
	@OneToMany(orphanRemoval = true, mappedBy = "ticket")
	@Cascade(CascadeType.ALL)
	private List<TicketProducto> productos;
	@ManyToOne
	private Cliente cliente;
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	@Transient
	private int idCliente;
	
	public Ticket() {
		super();
		this.fechaEmision = new Date();
		this.productos = new ArrayList<TicketProducto>();
	}
	
	public Ticket(List<TicketProducto> productos, Date fecha) {
		super();
		this.productos = productos;
		this.fechaEmision = fecha;
		this.productos = new ArrayList<TicketProducto>();
	}
	
	public Ticket(List<TicketProducto> productos, Cliente cliente) {
		super();
		this.productos = productos;
		this.cliente = cliente;
		this.fechaEmision = new Date();
	}

	public Ticket(List<TicketProducto> productos, int idCliente) {
		super();
		this.idCliente = idCliente;
		this.productos = productos;
		this.fechaEmision = new Date();
		this.productos = new ArrayList<TicketProducto>();
	}
	
	public float getTotal() {
		float total = 0;
		for(TicketProducto tp: this.productos){
			total += tp.getProducto().getMonto() * tp.getCantidadProducto();
		}
		return total;
	}

	
	
//	public Cliente getCliente() {
//		return cliente;
//	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public List<TicketProducto> getProductos() {
		return productos;
	}

	@Override
	public String toString() {
		return "Ticket [fechaEmision=" + fechaEmision + "]";
	}

	public void setProductos(List<TicketProducto> productos) {
		this.productos = productos;
	}

	public int getID() {
		return ID;
	}

//	@JsonIgnore
	public Cliente getCliente() {
		return this.cliente;
	}
	
	

}
