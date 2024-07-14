package com.immortalidiot.services;

import com.immortalidiot.entities.Client;
import com.immortalidiot.entities.Deal;
import com.immortalidiot.entities.Manager;

public interface DealService {
    Deal createDeal(Manager manager, Client client, String clientTerms, int amount);
}
