package entregable.Entregable4.repositorios;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entregable.Entregable4.entidades.Producto;
import entregable.Entregable4.entidades.TicketProducto;
import entregable.Entregable4.pojo.ClientReport;
import entregable.Entregable4.pojo.DayReport;
import entregable.Entregable4.pojo.ProductReport;

public interface RepositorioTicketProducto extends JpaRepository<TicketProducto, Integer> {
	
	@Query("SELECT new entregable.Entregable4.pojo.ClientReport(c.nombre, SUM(tp.cantidadProducto * p.monto))"
			+ " FROM TicketProducto tp"
			+ " JOIN tp.ticket t"
			+ " JOIN tp.producto p"
			+ " JOIN t.cliente c"
			+ " GROUP BY c")
	public List<ClientReport> getClientsReport();
	
	@Query("SELECT new entregable.Entregable4.pojo.DayReport(year(t.fechaEmision)||'-'||month(t.fechaEmision)||'-'||day( t.fechaEmision), SUM(tp.cantidadProducto * p.monto))"
			+ " FROM TicketProducto tp"
			+ " JOIN tp.ticket t"
			+ " JOIN tp.producto p"
			+ " GROUP BY year(t.fechaEmision)||'-'||month(t.fechaEmision)||'-'||day( t.fechaEmision)")
	public List<DayReport> getDayReport();
	
	@Query("SELECT new entregable.Entregable4.pojo.ProductReport(p, SUM(tp.cantidadProducto * p.monto))"
			+ " FROM TicketProducto tp"
			+ " JOIN tp.producto p"
			+ " GROUP BY tp.producto"
			+ " ORDER BY (SUM(tp.cantidadProducto * p.monto)) DESC")
	public ProductReport getMostSoldProduct(PageRequest limitOne);
	
}
