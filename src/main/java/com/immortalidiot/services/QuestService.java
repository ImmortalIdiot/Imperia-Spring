package com.immortalidiot.services;

import com.immortalidiot.entities.enums.QuestType;

public interface QuestService {
    void createQuest();
    int needCultists(long urgency);
    String setMinRankAndGrade(QuestType questType);
    String setReward(QuestType questType);
    String setPunishment(QuestType questType);
}
