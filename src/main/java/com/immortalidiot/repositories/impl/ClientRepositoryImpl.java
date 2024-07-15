package com.immortalidiot.repositories.impl;

import com.immortalidiot.entities.Client;
import com.immortalidiot.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class ClientRepositoryImpl extends BaseRepository<Client, String> implements ClientRepository {
    @Override
    public Client findByContact(String contact) {
        String jpql = "SELECT c FROM Client c WHERE c.contact = :contact";
        TypedQuery<Client> query = entityManager.createQuery(jpql, Client.class);
        query.setParameter("contact", contact);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            // TODO: replace with custom exception
            throw new EntityNotFoundException("Client with such contact does not exist");
        }
    }
}
