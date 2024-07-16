package com.immortalidiot.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cultists")
public class Cultist {
    private String nickname;
    private String lastName;
    private String firstName;
    private String grade;
    private String rank;
    private City city;
    private int thanksGiving;
    private Set<Quest> quests = new HashSet<>();

    public Cultist(String nickname,
                   String lastName,
                   String firstName,
                   String grade,
                   String rank,
                   City city,
                   Integer thanksGiving) {
        this.nickname = nickname;
        this.lastName = lastName;
        this.firstName = firstName;
        this.grade = grade;
        this.rank = rank;
        this.city = city;
        this.thanksGiving = thanksGiving;
    }

    protected Cultist() {}

    @Id
    @Column(name = "nickname", nullable = false)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "grade", nullable = false)
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Column(name = "rank", nullable = false)
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @ManyToOne
    @JoinColumn(name = "city", nullable = false)
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Column(name = "thanks_giving", nullable = false)
    public int getThanksGiving() {
        return thanksGiving;
    }

    public void setThanksGiving(int thanksGiving) {
        if (thanksGiving > 0) { this.thanksGiving = thanksGiving; }
        else throw new IllegalArgumentException("Incorrect thanks giving count");
    }

    @ManyToMany(mappedBy = "cultists", targetEntity = Quest.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Quest> getQuests() {
        return quests;
    }

    public void setQuests(Set<Quest> quests) {
        this.quests = quests;
    }
}
