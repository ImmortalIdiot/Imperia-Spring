package com.immortalidiot.repositories;

import com.immortalidiot.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ClientRepository extends JpaRepository <Client, String> {
    Set<Client> findAllByContact(String contact);
}
