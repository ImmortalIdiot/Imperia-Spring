package com.immortalidiot.repositories;

import com.immortalidiot.entities.Deal;
import org.springframework.stereotype.Component;

@Component
public interface DealRepository {
    Deal findLatestDeal();
    void save(Deal deal);
}
