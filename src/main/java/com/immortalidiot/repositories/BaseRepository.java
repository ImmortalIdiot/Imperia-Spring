package com.immortalidiot.repositories;

public interface BaseRepository<EntityType, EntityPrimaryKeyType> {
    void save(EntityType entity);
    EntityType findById(Class<EntityType> entityTypeClass, EntityPrimaryKeyType id);
    void update(EntityType entity);
}
