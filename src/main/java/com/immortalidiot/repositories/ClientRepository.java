package com.immortalidiot.repositories;

import com.immortalidiot.entities.Client;

public interface ClientRepository {
    Client findByContact(String contact);
}
