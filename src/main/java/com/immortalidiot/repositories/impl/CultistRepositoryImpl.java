package com.immortalidiot.repositories.impl;

import com.immortalidiot.entities.Cultist;
import com.immortalidiot.entities.Quest;
import com.immortalidiot.repositories.CultistRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CultistRepositoryImpl extends BaseRepository<Cultist, String> implements CultistRepository {
    @Override
    @Transactional
    public Cultist findByNickname(String nickname) {
        String jpql = "SELECT c FROM Cultist c WHERE c.nickname = :nickname";
        TypedQuery<Cultist> query = entityManager.createQuery(jpql, Cultist.class);
        query.setParameter("nickname", nickname);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            // TODO: replace with custom exception
            throw new EntityNotFoundException("Cultist with such nickname does not exist");
        }
    }

    @Override
    public List<Quest> findQuestsByCultistId(String id) {
        String jpql = "SELECT q FROM Quest q WHERE q.cultist.nickname = :nickname";
        return entityManager.createQuery(jpql, Quest.class)
                .setParameter("nickname", id)
                .getResultList();
    }
}
