package com.immortalidiot.repositories.impl;

import com.immortalidiot.repositories.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public abstract class BaseRepositoryImpl <EntityType, EntityPrimaryKeyType>
        implements BaseRepository<EntityType, EntityPrimaryKeyType> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<EntityType> entityTypeClass;

    @Override
    public void save(EntityType entity) {
        entityManager.persist(entity);
    }

    @Override
    public EntityType findById(Class<EntityType> entityTypeClass, EntityPrimaryKeyType id) {
        EntityType entity = entityManager.find(entityTypeClass, id);
        return Optional.ofNullable(entity).orElseThrow(() ->
                new EntityNotFoundException(entityTypeClass.getSimpleName() + " with id " + id + " not found"));
    }

    @Override
    public void update(EntityType entity) {
        entityManager.merge(entity);
    }
}
