package com.immortalidiot.repositories.impl;

import com.immortalidiot.entities.Deal;
import com.immortalidiot.repositories.DealRepository;
import com.immortalidiot.util.exceptions.DealNotFoundException;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Repository;

@Repository
public class DealRepositoryImpl extends BaseRepositoryImpl<Deal, Long> implements DealRepository {

    @Override
    public Deal findLatestDeal() {
        String jpql = "SELECT d FROM Deal d WHERE d.id = (SELECT MAX(deal.id) FROM Deal deal)";
        try {
            return entityManager.createQuery(jpql, Deal.class).getSingleResult();
        } catch (NoResultException e) {
            throw new DealNotFoundException("No latest deal found");
        }
    }
}
