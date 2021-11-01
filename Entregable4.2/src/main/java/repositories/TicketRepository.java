package repositories;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import models.Ticket;
import pojo.ClientReport;
import pojo.MostSoldProduct;
import pojo.SalesPerDay;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
	@Query("SELECT new pojo.ClientReport(c.name, c.surname, SUM(pt.quantity * p.prize))"
			+ " FROM ProductTicket pt"
			+ " JOIN pt.ticket t"
			+ " JOIN pt.product p"
			+ " JOIN t.client c"
			+ " GROUP BY c")
	public List<ClientReport> getClientsReport();
	
	// TODO Ver cómo meter los productos de un mismo día a una lista. (Ver en POSTMAN) {{url}}/tickets/sales-per-day
	
	@Query("SELECT new pojo.SalesPerDay(DAY(t.created_at), pt.product.name, pt.quantity, SUM(pt.product.prize * pt.quantity))"
			+ " FROM ProductTicket pt"
			+ " JOIN pt.ticket t"
			+ " GROUP BY t.created_at, pt.product"
			+ " ORDER BY t.created_at")
	public List<SalesPerDay> getSalesPerDay();
	
	@Query("SELECT new pojo.MostSoldProduct(p.name, SUM(pt.quantity * p.prize), SUM(pt.quantity), p.prize)"
			+ " FROM ProductTicket pt"
			+ " JOIN pt.product p"
			+ " GROUP BY pt.quantity"
			+ " ORDER BY pt.quantity DESC")
	public MostSoldProduct getMostSoldProduct(PageRequest limitOne);
}