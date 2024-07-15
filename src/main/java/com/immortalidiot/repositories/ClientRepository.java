package com.immortalidiot.repositories;

import com.immortalidiot.entities.Client;
import org.springframework.stereotype.Component;

@Component
public interface ClientRepository {
    Client findByContact(String contact);
    void save(Client client);

}
