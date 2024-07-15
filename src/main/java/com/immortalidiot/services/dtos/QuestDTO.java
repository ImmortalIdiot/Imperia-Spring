package com.immortalidiot.services.dtos;

import com.immortalidiot.entities.Cultist;
import com.immortalidiot.entities.Deal;
import com.immortalidiot.entities.enums.QuestStatus;
import com.immortalidiot.entities.enums.QuestType;

import java.time.OffsetDateTime;
import java.util.Set;

public class QuestDTO {
    private Long id;
    private QuestType questType;
    private QuestStatus questStatus;
    private String minGrade;
    private String minRank;
    private int numCultists;
    private OffsetDateTime dateFormed;
    private OffsetDateTime dateCompleted;
    private String reward;
    private String punishment;
    private int chance;
    private Set<Cultist> cultists;
    private Deal deal;

    public QuestDTO(Long id,
                    QuestType questType,
                    QuestStatus questStatus,
                    String minGrade,
                    String minRank,
                    int numCultists,
                    OffsetDateTime dateFormed,
                    OffsetDateTime dateCompleted,
                    String reward, String punishment,
                    int chance,
                    Set<Cultist> cultists,
                    Deal deal) {
        this.id = id;
        this.questType = questType;
        this.questStatus = questStatus;
        this.minGrade = minGrade;
        this.minRank = minRank;
        this.numCultists = numCultists;
        this.dateFormed = dateFormed;
        this.dateCompleted = dateCompleted;
        this.reward = reward;
        this.punishment = punishment;
        this.chance = chance;
        this.cultists = cultists;
        this.deal = deal;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public QuestType getQuestType() { return questType; }

    public void setQuestType(QuestType questType) { this.questType = questType; }

    public QuestStatus getQuestStatus() { return questStatus; }

    public void setQuestStatus(QuestStatus questStatus) { this.questStatus = questStatus; }

    public String getMinGrade() { return minGrade; }

    public void setMinGrade(String minGrade) { this.minGrade = minGrade; }

    public String getMinRank() { return minRank; }

    public void setMinRank(String minRank) { this.minRank = minRank; }

    public int getNumCultists() { return numCultists; }

    public void setNumCultists(int numCultists) { this.numCultists = numCultists; }

    public OffsetDateTime getDateFormed() { return dateFormed; }

    public void setDateFormed(OffsetDateTime dateFormed) { this.dateFormed = dateFormed; }

    public OffsetDateTime getDateCompleted() { return dateCompleted; }

    public void setDateCompleted(OffsetDateTime dateCompleted) { this.dateCompleted = dateCompleted; }

    public String getReward() { return reward; }

    public void setReward(String reward) { this.reward = reward; }

    public String getPunishment() { return punishment; }

    public void setPunishment(String punishment) { this.punishment = punishment; }

    public int getChance() { return chance; }

    public void setChance(int chance) { this.chance = chance; }

    public Set<Cultist> getCultists() { return cultists; }

    public void setCultists(Set<Cultist> cultists) { this.cultists = cultists; }

    public Deal getDeal() { return deal; }

    public void setDeal(Deal deal) { this.deal = deal; }
}
