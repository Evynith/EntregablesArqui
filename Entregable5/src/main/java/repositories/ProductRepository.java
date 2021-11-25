package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import models.Client;
import models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT SUM(pt.quantity) "
			+ "FROM ProductTicket pt "
			+ "WHERE pt.ticket.client = ?1 "
			+ "AND pt.product = ?2 "
			+ "AND CURRENT_DATE = pt.ticket.created_at ")
	public Integer getCantProductOfDay(Client c, Product p);

}