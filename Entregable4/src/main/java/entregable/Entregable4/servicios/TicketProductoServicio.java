package entregable.Entregable4.servicios;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import entregable.Entregable4.entidades.Cliente;
import entregable.Entregable4.entidades.Producto;
import entregable.Entregable4.entidades.TicketProducto;
import entregable.Entregable4.pojo.ClientReport;
import entregable.Entregable4.pojo.DayReport;
import entregable.Entregable4.pojo.ProductLimit;
import entregable.Entregable4.pojo.ProductReport;
import entregable.Entregable4.repositorios.RepositorioTicketProducto;

@Service
public class TicketProductoServicio {

	@Autowired
	private RepositorioTicketProducto ticketProducto;
	@Autowired
	private ProductoServicio productoServicio;
	
	public boolean add(TicketProducto tp) {
		if (tp.getProducto().getCantidad() > tp.getCantidadProducto()) {
			this.productoServicio.actualizaStockEgreso(tp.getProducto(), tp.getCantidadProducto());
			return this.ticketProducto.save(tp) != null;			
		} else {
			return false;
		}
	}

	public void delete(TicketProducto tp) {
		this.productoServicio.actualizaStockIngreso(tp.getProducto(), tp.getCantidadProducto()) ;
		this.ticketProducto.deleteById(tp.getID());
	}

	public Optional<TicketProducto> getById(int idElement) {
		return this.ticketProducto.findById(idElement);
	}

	public boolean put(TicketProducto ticketProducto, int viejaCantidad) {
		int cantActual = ticketProducto.getProducto().getCantidad();
		int nvaCantidad = ticketProducto.getCantidadProducto();
		if ( nvaCantidad <= cantActual) {
			if (nvaCantidad > viejaCantidad) {
				this.productoServicio.actualizaStockEgreso(ticketProducto.getProducto(), nvaCantidad - viejaCantidad);
			} else {
				this.productoServicio.actualizaStockIngreso(ticketProducto.getProducto(), viejaCantidad - nvaCantidad);
			}
			this.ticketProducto.flush();
			return true;
		} else {
			return false;
		}
	}
	
	public List<ClientReport> getReporteClientes(){
		return this.ticketProducto.getClientsReport();
	}
	public List<DayReport> getReporteVentas(){
		return this.ticketProducto.getDayReport();
	}
	public ProductReport getMasVendido(){
		PageRequest limitOne = PageRequest.of(0, 1);
		return this.ticketProducto.getMostSoldProduct(limitOne);
	}

	public Integer getCantidadRestante(int c, int idProducto, int cantidadTotal, Date fecha){
		PageRequest limitOne = PageRequest.of(0, 1);
		 Calendar calendar = Calendar.getInstance();
	      calendar.setTime(fecha);
		String dia = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH)+1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
		ProductLimit pl = this.ticketProducto.getRestantProduct(c, idProducto, dia, limitOne);
		if (pl != null) {
			int cantidadActual = pl.getCantidadActual();
			return cantidadTotal - cantidadActual;
		}
		return cantidadTotal;
	}
}
