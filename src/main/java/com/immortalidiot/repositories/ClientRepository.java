package com.immortalidiot.repositories;

import com.immortalidiot.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    @Query(value = "SELECT c FROM Client c WHERE c.contact = :contact")
    Optional<Client> findByContact(@Param("contact") String contact);
}
