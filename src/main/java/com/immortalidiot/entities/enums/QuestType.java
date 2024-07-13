package com.immortalidiot.entities.enums;

public enum QuestType {
    CITIZEN_HELP ("Citizen help", 0),
    BODYGUARDS ("Bodyguards", 1),
    INTELLIGENCE ("Intelligence", 2),
    FORGERY ("Forgery", 3),
    MURDER ("Murder", 4);

    private String questType;
    private int number;

    QuestType(String questType, int number) {
        this.questType = questType;
        this.number = number;
    }
}
