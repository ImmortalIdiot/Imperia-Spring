package com.immortalidiot.repositories;

public interface BaseRepository<EntityType, EntityPrimaryKeyType> {
    void save(EntityType entity);
    EntityType findById(Class<EntityType> entityTypeClass, EntityPrimaryKeyType id);
    EntityType update(EntityType entity);
}
