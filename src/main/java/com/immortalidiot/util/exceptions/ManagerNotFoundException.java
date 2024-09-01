package com.immortalidiot.util.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class ManagerNotFoundException extends EntityNotFoundException {

    public ManagerNotFoundException() {
        super("Manager not found");
    }

    public ManagerNotFoundException(String message) {
        super(message);
    }
}
