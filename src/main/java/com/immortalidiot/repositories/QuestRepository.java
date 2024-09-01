package com.immortalidiot.repositories;

import com.immortalidiot.entities.Cultist;
import com.immortalidiot.entities.Quest;
import com.immortalidiot.entities.enums.QuestStatus;

import java.util.List;

public interface QuestRepository extends BaseRepository<Quest, Long> {
    List<Quest> findQuestsByCultists(List<Cultist> cultists, List<QuestStatus> statuses);
    List<Quest> findQuestsByQuestStatus(QuestStatus questStatus);
    List<Quest> findByMinGradeAndMinRankAndQuestStatus(String minGrade, String minRank, QuestStatus questStatus);
}
