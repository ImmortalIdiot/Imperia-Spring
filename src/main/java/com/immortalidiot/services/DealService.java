package com.immortalidiot.services;

import com.immortalidiot.services.dtos.ClientDTO;
import com.immortalidiot.services.dtos.DealDTO;

public interface DealService {
    DealDTO createDeal(ClientDTO clientDTO, String clientTerms);
}
