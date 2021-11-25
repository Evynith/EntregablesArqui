package entregable.Entregable4.repositorios;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import entregable.Entregable4.entidades.Cliente;
import entregable.Entregable4.entidades.Producto;
import entregable.Entregable4.entidades.TicketProducto;
import entregable.Entregable4.pojo.ClientReport;
import entregable.Entregable4.pojo.DayReport;
import entregable.Entregable4.pojo.ProductLimit;
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
	
//	@Query("SELECT new entregable.Entregable4.pojo.ProductLimit(year(t.fechaEmision)||'-'||month(t.fechaEmision)||'-'||day( t.fechaEmision), SUM(tp.cantidadProducto))"
//			+ " FROM TicketProducto tp"
//			+ " JOIN tp.producto p"
//			+ " WHERE tp.ticket.cliente.id = :persona AND p.id = :prod"
//			+ " GROUP BY year(t.fechaEmision)||'-'||month(t.fechaEmision)||'-'||day( t.fechaEmision)")
//	public ProductLimit getRestantProduct(@Param("persona")int cliente, @Param("prod")int producto,PageRequest limitOne);
//	public List<ProductLimit> getRestantProduct(@Param("persona")int cliente, @Param("prod")int producto);

//		@Query("SELECT new entregable.Entregable4.pojo.ProductLimit(year(t.fechaEmision)||'-'||month(t.fechaEmision)||'-'||day( t.fechaEmision), SUM(tp.cantidadProducto))"
//			+ " FROM TicketProducto tp"
//			+ " JOIN tp.ticket t"
//			+ " JOIN tp.producto p"
//			+ " WHERE t.cliente.id = ?1 AND p.id = ?2"
//			+ " GROUP BY year(t.fechaEmision)||'-'||month(t.fechaEmision)||'-'||day( t.fechaEmision)")
//	public ProductLimit getRestantProduct(int idCliente, int idProducto,PageRequest limitOne);
//	public ProductLimit getRestantProduct(int cliente, int producto,PageRequest limitOne);
	
	@Query("SELECT new entregable.Entregable4.pojo.ProductLimit(year(tp.ticket.fechaEmision)||'-'||month(tp.ticket.fechaEmision)||'-'||day( tp.ticket.fechaEmision), SUM(tp.cantidadProducto))"
			+ " FROM TicketProducto tp"
			+ " WHERE tp.ticket.cliente.id = ?1 AND tp.producto.id = ?2"
			+ " GROUP BY year(tp.ticket.fechaEmision)||'-'||month(tp.ticket.fechaEmision)||'-'||day( tp.ticket.fechaEmision)"
			+ " HAVING year(tp.ticket.fechaEmision)||'-'||month(tp.ticket.fechaEmision)||'-'||day( tp.ticket.fechaEmision) = ?3")
	public ProductLimit getRestantProduct(int idCliente, int idProducto, String fecha ,PageRequest limitOne);
}
