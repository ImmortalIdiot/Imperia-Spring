package com.immortalidiot.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
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

    private Class<EntityType> entityTypeClass;

    @Transactional
    public void save(EntityType entity) {
        entityManager.persist(entity);
    }

    @Transactional
    public EntityType findById(EntityPrimaryKeyType id) {
        Optional<EntityType> optionalEntity = genericRepository.findById(id);
        return optionalEntity.orElseThrow(() ->
                new EntityNotFoundException(entityTypeClass.getSimpleName() + " with id " + id + " not found"));
    }

    @Transactional
    public EntityType update(EntityType entity) {
        return entityManager.merge(entity);
    }

}
