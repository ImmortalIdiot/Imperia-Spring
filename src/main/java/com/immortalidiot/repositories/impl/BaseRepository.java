package com.immortalidiot.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public abstract class BaseRepository<EntityType, EntityPrimaryKeyType> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<EntityType> entityTypeClass;

    public void save(EntityType entity) {
        entityManager.persist(entity);
    }

    public EntityType findById(Class<EntityType> entityTypeClass, EntityPrimaryKeyType id) {
        EntityType entity = entityManager.find(entityTypeClass, id);
        return Optional.ofNullable(entity).orElseThrow(() ->
                new EntityNotFoundException(entityTypeClass.getSimpleName() + " with id " + id + " not found"));
    }

    public EntityType update(EntityType entity) {
        return entityManager.merge(entity);
    }

}
