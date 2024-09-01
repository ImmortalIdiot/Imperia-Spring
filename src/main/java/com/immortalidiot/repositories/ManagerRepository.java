package com.immortalidiot.repositories;

import com.immortalidiot.entities.Manager;

import java.util.List;

public interface ManagerRepository extends BaseRepository<Manager, String> {
    List<Manager> getAllManagers();
    Manager getRandomManager(List<Manager> managers);
}
