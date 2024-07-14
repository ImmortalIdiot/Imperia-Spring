package com.immortalidiot.services;

import com.immortalidiot.entities.Client;
import com.immortalidiot.entities.Deal;
import com.immortalidiot.entities.Manager;
import com.immortalidiot.services.dtos.ClientDTO;

public interface DealService {
    Deal createDeal(String managerCode, ClientDTO clientDTO, String clientTerms, int amount);
}
