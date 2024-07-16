package com.immortalidiot.util.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class QuestNotFoundException extends EntityNotFoundException {
    public QuestNotFoundException() {
        super("Quest not found");
    }

    public QuestNotFoundException(String message) {
        super(message);
    }
}
