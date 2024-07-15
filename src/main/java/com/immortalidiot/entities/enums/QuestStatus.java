package com.immortalidiot.entities.enums;

public enum QuestStatus {
    FORMED ("Formed", 0),
    AVAILABLE ("Available", 1),
    ONGOING ("Ongoing", 2),
    COMPLETED ("Completed", 3),
    FAILED ("Failed", 4);

    private final String statusName;
    private final int number;

    QuestStatus(String statusName, int number) {
        this.statusName = statusName;
        this.number = number;
    }

    public String getStatusName() { return statusName; }

    public int getNumber() { return number; }
}
