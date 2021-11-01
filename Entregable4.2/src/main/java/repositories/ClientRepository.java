package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {


}