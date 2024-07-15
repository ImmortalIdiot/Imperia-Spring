package com.immortalidiot.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public abstract class BaseRepository<EntityType, EntityPrimaryKeyType> {
    private JpaRepository<EntityType, EntityPrimaryKeyType> genericRepository;

    @PersistenceContext
    protected EntityManager entityManager;

    @Transactional
    public void save(EntityType entity) {
        entityManager.persist(entity);
    }

    @Transactional
    public Optional<EntityType> findById(EntityPrimaryKeyType id) {
        return genericRepository.findById(id);
    }

    @Transactional
    public EntityType update(EntityType entity) {
        return entityManager.merge(entity);
    }

}
