package com.immortalidiot.services.impl;

import com.immortalidiot.entities.Client;
import com.immortalidiot.repositories.ClientRepository;
import com.immortalidiot.services.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class ClientServiceImplementation implements ClientService {
    private ClientRepository clientRepository;

    @Override
    @Transactional
    public void registerClient(Client client) {
        Set<Client> existingClients = clientRepository.findAllByContact(client.getContact());
        if (existingClients.isEmpty()) { clientRepository.save(client); }
    }
}
