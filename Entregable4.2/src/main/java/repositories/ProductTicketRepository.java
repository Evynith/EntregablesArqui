package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import models.ProductTicket;

@Repository
public interface ProductTicketRepository extends JpaRepository<ProductTicket, Long> {

}
