package com.immortalidiot.repositories;

import com.immortalidiot.entities.Client;
import org.springframework.stereotype.Component;

@Component
public interface ClientRepository extends BaseRepository<Client, String> {

}
