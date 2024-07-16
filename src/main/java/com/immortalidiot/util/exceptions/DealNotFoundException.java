package com.immortalidiot.util.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class DealNotFoundException extends EntityNotFoundException {
    public DealNotFoundException() {
        super("Deal not found");
    }
}
