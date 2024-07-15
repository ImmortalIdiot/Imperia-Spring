package com.immortalidiot.repositories;

import com.immortalidiot.entities.Deal;

public interface DealRepository {
    Deal findLatestDeal();
}
