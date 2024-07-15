package com.immortalidiot.services.impl;

import com.immortalidiot.entities.Client;
import com.immortalidiot.repositories.ClientRepository;
import com.immortalidiot.services.ClientService;
import com.immortalidiot.services.dtos.ClientDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientServiceImplementation implements ClientService {
    private ClientRepository clientRepository;

    @Override
    @Transactional
    public void registerClient(ClientDTO clientDTO) {
        Client client = clientRepository.findByContact(clientDTO.getContact()).orElse(null);
        if (client != null) { clientRepository.save(client); }
    }
}
