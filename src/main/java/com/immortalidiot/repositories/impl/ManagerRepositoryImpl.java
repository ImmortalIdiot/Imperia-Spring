package com.immortalidiot.repositories.impl;

import com.immortalidiot.entities.Manager;
import com.immortalidiot.repositories.ManagerRepository;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Repository
public class ManagerRepositoryImpl extends BaseRepository<Manager, String> implements ManagerRepository {
    @Override
    @Transactional(readOnly = true)
    public List<Manager> getAllManagers() {
        String selectAllManagers = "Select m FROM Manager m";
        TypedQuery<Manager> query = entityManager.createQuery(selectAllManagers, Manager.class);
        return query.getResultList();
    }

    public Manager getRandomManager(List<Manager> managers) {
        if (managers.isEmpty()) { throw new IllegalStateException("Managers not founded"); }
        Random randomizer = new Random();
        return managers.get(randomizer.nextInt(managers.size()));
    }
}
