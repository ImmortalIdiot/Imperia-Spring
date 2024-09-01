package com.immortalidiot.repositories.impl;

import com.immortalidiot.entities.Manager;
import com.immortalidiot.repositories.ManagerRepository;
import com.immortalidiot.util.exceptions.ManagerNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Repository
public class ManagerRepositoryImpl extends BaseRepositoryImpl<Manager, String> implements ManagerRepository {

    @Override
    @Transactional(readOnly = true)
    public List<Manager> getAllManagers() {
        String selectAllManagers = "Select m FROM Manager m";
        List<Manager> managers = entityManager.createQuery(selectAllManagers, Manager.class).getResultList();

        if (managers.isEmpty()) {
            throw new ManagerNotFoundException("Managers do not exist");
        }

        return managers;
    }

    public Manager getRandomManager(List<Manager> managers) {
        if (managers.isEmpty()) { throw new ManagerNotFoundException("Managers not founded"); }
        Random randomizer = new Random();
        return managers.get(randomizer.nextInt(managers.size()));
    }
}
