package com.immortalidiot.services.dtos;

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
    private Set<String> cultistsNicknames;
    private long dealId;

    public QuestDTO() {
    }

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
                    Set<String> cultists,
                    long dealId) {
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
        this.cultistsNicknames = cultists;
        this.dealId = dealId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestType getQuestType() {
        return questType;
    }

    public void setQuestType(QuestType questType) {
        this.questType = questType;
    }

    public QuestStatus getQuestStatus() {
        return questStatus;
    }

    public void setQuestStatus(QuestStatus questStatus) {
        this.questStatus = questStatus;
    }

    public String getMinGrade() {
        return minGrade;
    }

    public void setMinGrade(String minGrade) {
        this.minGrade = minGrade;
    }

    public String getMinRank() {
        return minRank;
    }

    public void setMinRank(String minRank) {
        this.minRank = minRank;
    }

    public int getNumCultists() {
        return numCultists;
    }

    public void setNumCultists(int numCultists) {
        this.numCultists = numCultists;
    }

    public OffsetDateTime getDateFormed() {
        return dateFormed;
    }

    public void setDateFormed(OffsetDateTime dateFormed) {
        this.dateFormed = dateFormed;
    }

    public OffsetDateTime getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(OffsetDateTime dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getPunishment() {
        return punishment;
    }

    public void setPunishment(String punishment) {
        this.punishment = punishment;
    }

    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    public Set<String> getCultists() {
        return cultistsNicknames;
    }

    public void setCultists(Set<String> cultistsNicknames) {
        this.cultistsNicknames = cultistsNicknames;
    }

    public long getDeal() {
        return dealId;
    }

    public void setDeal(long dealId) {
        this.dealId = dealId;
    }

    @Override
    public String toString() {
        return getId() + " " + getChance() + " " + getNumCultists() + " " +
                getQuestStatus().getStatusName() + " " + getQuestType().getQuestType();
    }
}
