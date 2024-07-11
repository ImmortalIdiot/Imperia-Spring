package com.immortalidiot.repositories;

import com.immortalidiot.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository <Client, String> {

}
