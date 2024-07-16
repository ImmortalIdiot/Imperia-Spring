package com.immortalidiot.util.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class CultistNotFoundException extends EntityNotFoundException {
    public CultistNotFoundException() {
        super("Cultist not found");
    }
}
