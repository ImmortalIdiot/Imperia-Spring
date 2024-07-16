package com.immortalidiot.services.dtos;

public class CultistDTO {
    private String nickname;
    private String grade;
    private String rank;

    protected CultistDTO() {}

    public CultistDTO(String nickname) { this.nickname = nickname; }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
