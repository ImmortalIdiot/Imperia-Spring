package com.immortalidiot.repositories;

import com.immortalidiot.entities.Deal;
import org.springframework.stereotype.Component;

@Component
public interface DealRepository extends BaseRepository<Deal, Long> {
    Deal findLatestDeal();
}
