package entregable.Entregable4.pojo;

import java.util.Date;
import java.util.List;

import entregable.Entregable4.entidades.Cliente;
import entregable.Entregable4.entidades.Producto;

public class TicketPOJO {
//	private int ID;
	private Date fechaEmision; 
	private int cantidadProducto;
	private String producto;
	private String cliente;
	
	public TicketPOJO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TicketPOJO(Date fechaEmision, int cantidadProducto, String producto, String nombreCliente) {
		super();
//		ID = iD;
		this.fechaEmision = fechaEmision;
		this.cantidadProducto = cantidadProducto;
		this.producto = producto;
		this.cliente = nombreCliente;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public int getCantidadProducto() {
		return cantidadProducto;
	}

	public String getProducto() {
		return producto;
	}

	public String getCliente() {
		return cliente;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	
	
}
