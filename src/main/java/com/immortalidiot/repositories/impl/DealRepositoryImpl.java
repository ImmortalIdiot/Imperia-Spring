package com.immortalidiot.repositories.impl;

import com.immortalidiot.entities.Deal;
import com.immortalidiot.repositories.DealRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

public class DealRepositoryImpl extends BaseRepository<Deal, Long> implements DealRepository {
    @Override
    @Transactional
    public Deal findLatestDeal() {
        String jpql = "SELECT d FROM Deal d WHERE d.id = (SELECT MAX(deal.id) FROM Deal deal)";
        try {
            return entityManager.createQuery(jpql, Deal.class).getSingleResult();
        } catch (NoResultException e) {
            // TODO: replace with custom exception
            throw new EntityNotFoundException("No latest deal found");
        }
    }
}
