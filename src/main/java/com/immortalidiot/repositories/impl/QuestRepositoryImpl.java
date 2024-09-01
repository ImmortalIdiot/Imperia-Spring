package com.immortalidiot.repositories.impl;

import com.immortalidiot.entities.Cultist;
import com.immortalidiot.entities.Quest;
import com.immortalidiot.entities.enums.QuestStatus;
import com.immortalidiot.repositories.QuestRepository;
import com.immortalidiot.util.exceptions.QuestNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestRepositoryImpl extends BaseRepositoryImpl<Quest, Long> implements QuestRepository {

    @Override
    public List<Quest> findQuestsByCultists(List<Cultist> cultists, List<QuestStatus> statuses) {
        String jpql = "SELECT q " +
                "FROM Quest q JOIN q.cultists c " +
                "WHERE c IN :cultists AND q.questStatus IN :statuses";
        List<Quest> quests = entityManager.createQuery(jpql, Quest.class)
                .setParameter("cultists", cultists)
                .setParameter("statuses", statuses)
                .getResultList();

        if (quests.isEmpty()) {
            throw new QuestNotFoundException("Quests do not exist");
        }

        return quests;
    }

    @Override
    public List<Quest> findQuestsByQuestStatus(QuestStatus questStatus) {
        String jpql = "SELECT q FROM Quest q WHERE q.questStatus = :status";
        List<Quest> quests = entityManager
                .createQuery(jpql, Quest.class)
                .setParameter("status", questStatus)
                .getResultList();

        if (quests.isEmpty()) {
            throw new QuestNotFoundException("Quests with status " + questStatus + " do not exist");
        }

        return quests;
    }

    @Override
    public List<Quest> findByMinGradeAndMinRankAndQuestStatus(String minGrade,
                                                              String minRank,
                                                              QuestStatus questStatus) {
        String jpql = "SELECT q FROM Quest q " +
                "WHERE q.minGrade = :minGrade AND q.minRank = :minRank AND q.questStatus = :status";
        List<Quest> quests = entityManager
                .createQuery(jpql, Quest.class)
                .setParameter("minGrade", minGrade)
                .setParameter("minRank", minRank)
                .setParameter("status", questStatus)
                .getResultList();

        if (quests.isEmpty()) {
            throw new QuestNotFoundException("Quests with " +
                    "status " + questStatus +
                    ", minGrade " + minGrade +
                    ", minRank " + minRank + " do not exist");
        }

        return quests;
    }
}
