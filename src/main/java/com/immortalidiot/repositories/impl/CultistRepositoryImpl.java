package com.immortalidiot.repositories.impl;

import com.immortalidiot.entities.Cultist;
import com.immortalidiot.entities.Quest;
import com.immortalidiot.repositories.CultistRepository;
import com.immortalidiot.util.exceptions.QuestNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CultistRepositoryImpl extends BaseRepositoryImpl<Cultist, String> implements CultistRepository {

    @Override
    public List<Quest> findQuestsByCultistId(String id) {
        String jpql = "SELECT q FROM Quest q JOIN q.cultists c WHERE c.nickname = :nickname";
        List<Quest> quests = entityManager.createQuery(jpql, Quest.class)
                .setParameter("nickname", id)
                .getResultList();

        if (quests.isEmpty()) {
            throw new QuestNotFoundException("No quests were found which " + id + " participants");
        }

        return quests;
    }
}
