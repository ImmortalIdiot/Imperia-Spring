package com.immortalidiot.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "quest")
public class Quest {
    private Long questId;
    private String questType;
    private String minGrade;
    private String minRank;
    private int numCultists;
    private Date dateFormed;
    private Date dateCompleted;
    private String reward;
    private String punishment;
    private Set<Cultist> cultists;
    private Deal deal;

    public Quest(String questType,
                 String minGrade,
                 String minRank,
                 int numCultists,
                 Date dateFormed,
                 Date dateCompleted,
                 String reward,
                 String punishment,
                 Set<Cultist> cultists,
                 Deal deal) {
        this.questType = questType;
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

    public Quest() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quest_id", nullable = false)
    public Long getQuestId() {
        return questId;
    }

    public void setQuestId(Long questId) {
        this.questId = questId;
    }

    @Column(name = "quest_type", nullable = false)
    public String getQuestType() {
        return questType;
    }

    public void setQuestType(String questType) {
        this.questType = questType;
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
    public Date getDateFormed() {
        return dateFormed;
    }

    public void setDateFormed(Date dateFormed) {
        this.dateFormed = dateFormed;
    }

    @Column(name = "date_completed", nullable = false)
    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
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
