package com.immortalidiot.repositories.impl;

import com.immortalidiot.entities.Client;
import com.immortalidiot.repositories.ClientRepository;
import com.immortalidiot.util.exceptions.ClientNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepositoryImpl extends BaseRepositoryImpl<Client, String> implements ClientRepository {
    @Override
    public Client findByContact(String contact) {
        String jpql = "SELECT c FROM Client c WHERE c.contact = :contact";
        TypedQuery<Client> query = entityManager.createQuery(jpql, Client.class);
        query.setParameter("contact", contact);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new ClientNotFoundException();
        }
    }
}
