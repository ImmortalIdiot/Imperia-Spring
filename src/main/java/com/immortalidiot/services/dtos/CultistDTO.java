package com.immortalidiot.services.dtos;

public class CultistDTO {
    private String nickname;

    public CultistDTO(String nickname) { this.nickname = nickname; }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
