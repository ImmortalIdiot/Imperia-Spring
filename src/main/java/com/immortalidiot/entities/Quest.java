package com.immortalidiot.entities;

import com.immortalidiot.entities.enums.QuestStatus;
import com.immortalidiot.entities.enums.QuestType;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@Table(name = "quest")
public class Quest {
    private QuestType questType;
    private QuestStatus questStatus;
    private String minGrade;
    private String minRank;
    private int numCultists;
    private OffsetDateTime dateFormed;
    private OffsetDateTime dateCompleted;
    private String reward;
    private String punishment;
    private Set<Cultist> cultists;
    private Deal deal;

    public Quest(QuestType questType,
                 String minGrade,
                 String minRank,
                 int numCultists,
                 OffsetDateTime dateFormed,
                 OffsetDateTime dateCompleted,
                 String reward,
                 String punishment,
                 Set<Cultist> cultists,
                 Deal deal) {
        this.questType = questType;
        this.questStatus = QuestStatus.FORMED;
        this.minGrade = minGrade;
        this.minRank = minRank;
        this.numCultists = numCultists;
        this.dateFormed = dateFormed;
        this.dateCompleted = dateCompleted;
        this.reward = reward;
        this.punishment = punishment;
        this.cultists = cultists;
        this.deal = deal;
    }

    protected Quest() {}

    @Column(name = "quest_type", nullable = false)
    public QuestType getQuestType() {
        return questType;
    }

    public void setQuestType(QuestType questType) {
        this.questType = questType;
    }

    @Column(name = "quest_status", nullable = false)
    public QuestStatus getQuestStatus() {
        return questStatus;
    }

    public void setQuestStatus(QuestStatus status) {
        this.questStatus = status;
    }

    @Column(name = "min_grade", nullable = false)
    public String getMinGrade() {
        return minGrade;
    }

    public void setMinGrade(String minGrade) {
        this.minGrade = minGrade;
    }

    @Column(name = "min_rank", nullable = false)
    public String getMinRank() {
        return minRank;
    }

    public void setMinRank(String minRank) {
        this.minRank = minRank;
    }

    @Column(name = "num_cultists", nullable = false)
    public int getNumCultists() {
        return numCultists;
    }

    public void setNumCultists(int numCultists) {
        this.numCultists = numCultists;
    }

    @Column(name = "date_formed", nullable = false)
    public OffsetDateTime getDateFormed() {
        return dateFormed;
    }

    public void setDateFormed(OffsetDateTime dateFormed) {
        this.dateFormed = dateFormed;
    }

    @Column(name = "date_completed", nullable = false)
    public OffsetDateTime getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(OffsetDateTime dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    @Column(name = "reward", nullable = false)
    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    @Column(name = "punishment", nullable = false)
    public String getPunishment() {
        return punishment;
    }

    public void setPunishment(String punishment) {
        this.punishment = punishment;
    }

    @ManyToMany
    @JoinTable(
            name = "task_cultist",
            joinColumns = @JoinColumn(name = "quest_id"),
            inverseJoinColumns = @JoinColumn(name = "cultist_nickname")
    )
    public Set<Cultist> getCultists() {
        return cultists;
    }

    public void setCultists(Set<Cultist> cultists) {
        this.cultists = cultists;
    }

    @OneToOne
    @JoinColumn(name = "deal_number")
    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }
}
