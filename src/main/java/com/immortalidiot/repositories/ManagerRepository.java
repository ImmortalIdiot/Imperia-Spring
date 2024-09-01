package com.immortalidiot.repositories;

import com.immortalidiot.entities.Manager;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ManagerRepository extends BaseRepository<Manager, String> {
    List<Manager> getAllManagers();
    Manager getRandomManager(List<Manager> managers);
}
