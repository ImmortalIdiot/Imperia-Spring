package com.immortalidiot.repositories;

import com.immortalidiot.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository <Manager, String> {

}
