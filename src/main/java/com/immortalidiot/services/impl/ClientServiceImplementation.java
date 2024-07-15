package com.immortalidiot.services.impl;

import com.immortalidiot.entities.Client;
import com.immortalidiot.repositories.impl.ClientRepositoryImpl;
import com.immortalidiot.services.ClientService;
import com.immortalidiot.services.dtos.ClientDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientServiceImplementation implements ClientService {
    private ClientRepositoryImpl clientRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ClientServiceImplementation(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void registerClient(ClientDTO clientDTO) {
        Client client = modelMapper.map(clientDTO, Client.class);

        if (client != null) {
            client.setContact(clientDTO.getContact());
            client.setName(clientDTO.getName());
            clientRepository.save(client);
        } else {
            // TODO: entityNotFoundException handler
        }
    }
}
