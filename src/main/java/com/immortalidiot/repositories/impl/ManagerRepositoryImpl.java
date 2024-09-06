package com.immortalidiot.repositories.impl;

import com.immortalidiot.entities.Manager;
import com.immortalidiot.repositories.ManagerRepository;
import com.immortalidiot.util.exceptions.ManagerNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ManagerRepositoryImpl extends BaseRepositoryImpl<Manager, String> implements ManagerRepository {

    @Override
    public List<Manager> getAllManagers() {
        String selectAllManagers = "Select m FROM Manager m";
        List<Manager> managers = entityManager.createQuery(selectAllManagers, Manager.class).getResultList();

        if (managers.isEmpty()) {
            throw new ManagerNotFoundException("Managers do not exist");
        }

        return managers;
    }
}
