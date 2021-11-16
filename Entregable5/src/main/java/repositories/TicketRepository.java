package repositories;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import models.Ticket;
import pojo.ClientReport;
import pojo.MostSoldProduct;
import pojo.SalesPerDay;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
	@Query("SELECT new pojo.ClientReport(c.name, c.surname, SUM(pt.quantity * p.price))"
			+ " FROM ProductTicket pt"
			+ " JOIN pt.ticket t"
			+ " JOIN pt.product p"
			+ " JOIN t.client c"
			+ " GROUP BY c")
	public List<ClientReport> getClientsReport();
	
	@Query("SELECT new pojo.SalesPerDay(t.created_at, SUM(pt.quantity), SUM(pt.product.price * pt.quantity))"
			+ " FROM ProductTicket pt"
			+ " JOIN pt.ticket t"
			+ " GROUP BY t.created_at"
			+ " ORDER BY t.created_at")
	public List<SalesPerDay> getSalesPerDay();
	
	@Query("SELECT new pojo.MostSoldProduct(p.name, SUM(pt.quantity * p.price), SUM(pt.quantity), p.price)"
			+ " FROM ProductTicket pt"
			+ " JOIN pt.product p"
			+ " GROUP BY pt.quantity"
			+ " ORDER BY pt.quantity DESC")
	public MostSoldProduct getMostSoldProduct(PageRequest limitOne);
}