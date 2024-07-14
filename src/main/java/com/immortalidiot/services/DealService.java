package com.immortalidiot.services;

import com.immortalidiot.entities.Deal;
import com.immortalidiot.services.dtos.ClientDTO;

public interface DealService {
    Deal createDeal(ClientDTO clientDTO, String clientTerms);
}
