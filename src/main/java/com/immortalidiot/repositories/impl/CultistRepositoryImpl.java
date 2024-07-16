package com.immortalidiot.repositories.impl;

import com.immortalidiot.entities.Cultist;
import com.immortalidiot.entities.Quest;
import com.immortalidiot.repositories.CultistRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CultistRepositoryImpl extends BaseRepository<Cultist, String> implements CultistRepository {
    @Override
    public List<Quest> findQuestsByCultistId(String id) {
        String jpql = "SELECT q FROM Quest q JOIN q.cultists c WHERE c.nickname = :nickname";
        return entityManager.createQuery(jpql, Quest.class)
                .setParameter("nickname", id)
                .getResultList();
    }
}
