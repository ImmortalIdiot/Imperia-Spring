package com.immortalidiot.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseServiceImpl {
    @PersistenceContext
    protected EntityManager entityManager;
}
