package com.immortalidiot.repositories;

import com.immortalidiot.entities.Deal;

public interface DealRepository extends BaseRepository<Deal, Long> {
    Deal findLatestDeal();
}
