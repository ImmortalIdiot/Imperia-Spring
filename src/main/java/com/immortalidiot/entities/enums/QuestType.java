package com.immortalidiot.entities.enums;

public enum QuestType {
    CITIZEN_HELP ("Citizen help", 0),
    BODYGUARDS ("Bodyguards", 1),
    INTELLIGENCE ("Intelligence", 2),
    FORGERY ("Forgery", 3),
    MURDER ("Murder", 4);

    private final String questType;
    private final int number;

    QuestType(String questType, int number) {
        this.questType = questType;
        this.number = number;
    }
    public String getQuestType() {
        return questType;
    }

    public int getNumber() {
        return number;
    }

    public static QuestType getQuestTypeByNumber(int number) {
        for (QuestType type : QuestType.values()) {
            if (type.getNumber() == number) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid type number: " + number);
    }

    public static QuestType getQuestTypeByName(String name) {
        for (QuestType type : QuestType.values()) {
            if (type.getQuestType().equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid type name: " + name);
    }
}
