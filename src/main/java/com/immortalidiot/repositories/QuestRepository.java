package com.immortalidiot.repositories;

import com.immortalidiot.entities.Cultist;
import com.immortalidiot.entities.Quest;
import com.immortalidiot.entities.enums.QuestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestRepository extends JpaRepository <Quest, Long> {
    @Query(value = "SELECT q " +
            "FROM Quest q JOIN q.cultists c " +
            "WHERE c IN :cultists and q.questStatus IN ('Completed', 'Failed')")
    List<Quest> findQuestsByCultists(@Param("cultists") List<Cultist> cultists);

    @Query(value = "SELECT q FROM Quest q WHERE q.questStatus = :status")
    List<Quest> findQuestsByQuestStatus(@Param("status") QuestStatus questStatus);

    @Query(value = "SELECT q " +
            "FROM Quest q " +
            "WHERE q.minGrade = :minGrade AND q.minRank = :minRank AND q.questStatus = :status")
    List<Quest> findByMinGradeAndMinRankAndQuestStatus(@Param("minGrade") String minGrade,
                                                       @Param("minRank") String minRank,
                                                       @Param("status") QuestStatus questStatus);
}
