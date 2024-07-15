package com.immortalidiot.services.dtos;

import com.immortalidiot.entities.Client;
import com.immortalidiot.entities.Manager;
import com.immortalidiot.entities.Quest;

public class DealDTO {
    private Manager manager;
    private Client client;
    private String clientTerms;
    private double amount;
    private Quest quest;

    public DealDTO(Manager manager, Client client, String clientTerms, double amount, Quest quest) {
        this.manager = manager;
        this.client = client;
        this.clientTerms = clientTerms;
        this.amount = amount;
        this.quest = quest;
    }

    public Manager getManager() { return manager; }

    public void setManager(Manager manager) { this.manager = manager; }

    public Client getClient() { return client; }

    public void setClient(Client client) { this.client = client; }

    public String getClientTerms() { return clientTerms; }

    public void setClientTerms(String clientTerms) { this.clientTerms = clientTerms; }

    public double getAmount() { return amount; }

    public void setAmount(double amount) { this.amount = amount; }

    public Quest getQuest() { return quest; }

    public void setQuest(Quest quest) { this.quest = quest; }
}
