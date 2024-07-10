package com.immortalidiot.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "quest")
public class Quest {
    private String taskId;
    private String taskType;
    private String minGrade;
    private String minRank;
    private int numCultists;
    private Date dateFormed;
    private Date dateCompleted;
    private double reward;
    private double punishment;
    private Set<Cultist> cultists;
    private Deal deal;

    public Quest(String taskId, String taskType, String minGrade, String minRank, int numCultists, Date dateFormed, Date dateCompleted, double reward, double punishment, Set<Cultist> cultists, Deal deal) {
        this.taskId = taskId;
        this.taskType = taskType;
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
    @Column(name = "task_id", nullable = false)
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Column(name = "task_type", nullable = false)
    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    @Column(name = "min_grade")
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
    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    @Column(name = "punishment", nullable = false)
    public double getPunishment() {
        return punishment;
    }

    public void setPunishment(double punishment) {
        this.punishment = punishment;
    }

    @ManyToMany
    @JoinTable(
            name = "task_cultist",
            joinColumns = @JoinColumn(name = "task_id"),
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
