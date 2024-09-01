package com.immortalidiot.repositories.impl;

import com.immortalidiot.entities.Client;
import com.immortalidiot.repositories.ClientRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepositoryImpl extends BaseRepositoryImpl<Client, String> implements ClientRepository {

}
