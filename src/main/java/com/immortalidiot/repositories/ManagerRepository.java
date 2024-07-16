package com.immortalidiot.repositories;

import com.immortalidiot.entities.Manager;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ManagerRepository {
    List<Manager> getAllManagers();
    Manager getRandomManager(List<Manager> managers);
}
