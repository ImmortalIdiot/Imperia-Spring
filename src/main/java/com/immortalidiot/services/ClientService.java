package com.immortalidiot.services;

import com.immortalidiot.entities.Client;

public interface ClientService {
    void registerClient(Client client);
    void getClientByContact(String contact);
}
