package com.immortalidiot.services;

import com.immortalidiot.services.dtos.ClientDTO;

public interface DealService {
    void createDeal(ClientDTO clientDTO, String clientTerms);
}
