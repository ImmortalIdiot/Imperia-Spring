package com.immortalidiot.repositories.impl;

import com.immortalidiot.entities.Cultist;
import com.immortalidiot.entities.Quest;
import com.immortalidiot.entities.enums.QuestStatus;
import com.immortalidiot.repositories.QuestRepository;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class QuestRepositoryImpl extends BaseRepository<Quest, Long> implements QuestRepository {
    @Override
    public List<Quest> findQuestsByCultists(List<Cultist> cultists, List<QuestStatus> statuses) {
        String jpql = "SELECT q " +
                "FROM Quest q JOIN q.cultists c " +
                "WHERE c IN :cultists AND q.questStatus IN :statuses";
        TypedQuery<Quest> query = entityManager.createQuery(jpql, Quest.class);
        query.setParameter("cultists", cultists);
        query.setParameter("statuses", statuses);
        return query.getResultList();
    }

    @Override
    public List<Quest> findQuestsByQuestStatus(QuestStatus questStatus) {
        String jpql = "SELECT q FROM Quest q WHERE q.questStatus = :status";
        TypedQuery<Quest> query = entityManager.createQuery(jpql, Quest.class);
        query.setParameter("status", questStatus);
        return query.getResultList();
    }

    @Override
    public List<Quest> findByMinGradeAndMinRankAndQuestStatus(String minGrade, String minRank, QuestStatus questStatus) {
        String jpql = "SELECT q FROM Quest q " +
                "WHERE q.minGrade = :minGrade AND q.minRank = :minRank AND q.questStatus = :status";
        TypedQuery<Quest> query = entityManager.createQuery(jpql, Quest.class);
        query.setParameter("minGrade", minGrade);
        query.setParameter("minRank", minRank);
        query.setParameter("status", questStatus);
        return query.getResultList();
    }
}
