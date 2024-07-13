package com.immortalidiot.services.impl;

import com.immortalidiot.entities.Manager;
import com.immortalidiot.services.ManagerService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class ManagerServiceImplementation implements ManagerService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Manager> getAllManagers() {
        String selectAllManagers = "Select m FROM Manager m";
        TypedQuery<Manager> query = entityManager.createQuery(selectAllManagers, Manager.class);
        return query.getResultList();
    }

    protected Manager getRandomManager(List<Manager> managers) {
        if (managers.isEmpty()) { throw new IllegalStateException("Managers not founded"); }
        Random randomizer = new Random();
        return managers.get(randomizer.nextInt(managers.size()));
    }
}
