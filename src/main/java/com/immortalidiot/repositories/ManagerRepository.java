package com.immortalidiot.repositories;

import com.immortalidiot.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ManagerRepository {
    List<Manager> getAllManagers();
}
