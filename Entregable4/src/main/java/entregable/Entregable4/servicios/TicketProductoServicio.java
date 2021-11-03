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
	
	public boolean add(TicketProducto tp) {
		return this.ticketProducto.save(tp) != null;
	}

	public void delete(int idElement) {
		this.ticketProducto.deleteById(idElement);
	}

	public Optional<TicketProducto> getById(int idElement) {
		return this.ticketProducto.findById(idElement);
	}

	public boolean put(TicketProducto ticketProducto2) {
		this.ticketProducto.flush();
		return true;
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
//		return cantidadTotal - this.ticketProducto.getRestantProduct(c, idProducto, limitOne).getCantidadActual();
//		return cantidadTotal - this.ticketProducto.getRestantProduct(c, idProducto,limitOne).getCantidadActual();
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
