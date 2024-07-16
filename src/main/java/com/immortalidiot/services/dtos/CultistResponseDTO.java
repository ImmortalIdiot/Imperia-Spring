package com.immortalidiot.services.dtos;

public class CultistResponseDTO extends CultistDTO {
    private String nickname;
    private String grade;
    private String rank;

    private boolean isPromoted;

    protected CultistResponseDTO() {}

    public CultistResponseDTO(String nickname, String grade, String rank, boolean isPromoted) {
        this.nickname = nickname;
        this.grade = grade;
        this.rank = rank;
        this.isPromoted = isPromoted;

    }

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

    public boolean isPromoted() {
        return isPromoted;
    }

    public void setPromoted(boolean promoted) {
        isPromoted = promoted;
    }

}
