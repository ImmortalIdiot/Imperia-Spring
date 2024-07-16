package com.immortalidiot.util.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class ClientNotFoundException extends EntityNotFoundException {
    public ClientNotFoundException() {
        super("Client not found");
    }

    public ClientNotFoundException(String message) {
        super(message);
    }
}
