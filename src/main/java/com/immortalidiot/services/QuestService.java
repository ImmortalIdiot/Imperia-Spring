package com.immortalidiot.services;

import com.immortalidiot.entities.Cultist;
import com.immortalidiot.entities.Quest;
import com.immortalidiot.entities.enums.QuestType;

import java.util.List;

public interface QuestService {
    void createQuest();
    int needCultists(long urgency);
    String setMinRankAndGrade(QuestType questType);
    String setReward(QuestType questType);
    String setPunishment(QuestType questType);
    List<Quest> getAllQuestsForCultist(Cultist cultist);
}
