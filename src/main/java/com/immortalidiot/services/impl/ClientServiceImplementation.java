package com.immortalidiot.services.impl;

import com.immortalidiot.entities.Client;
import com.immortalidiot.repositories.ClientRepository;
import com.immortalidiot.services.ClientService;
import com.immortalidiot.services.dtos.ClientDTO;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientServiceImplementation implements ClientService {

    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    public ClientServiceImplementation(ClientRepository clientRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void registerClient(ClientDTO clientDTO) {
        Client client = modelMapper.map(clientDTO, Client.class);
        client.setContact(clientDTO.getContact());
        client.setName(clientDTO.getName());

        if (isClientExist(client)) {
            clientRepository.save(client);
        }
    }

    private boolean isClientExist(Client client) {
        try {
            clientRepository.findById(Client.class, client.getContact());
            return true;
        } catch (EntityNotFoundException e) {
            return false;
        }
    }
}
