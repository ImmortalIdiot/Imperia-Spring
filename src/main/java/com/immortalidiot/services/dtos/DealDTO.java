package com.immortalidiot.services.dtos;

import com.immortalidiot.entities.Quest;

public class DealDTO {
    private ManagerDTO manager;
    private ClientDTO client;
    private String clientTerms;
    private double amount;
    private Quest quest;

    protected DealDTO() {
    }

    ;

    public DealDTO(ManagerDTO manager, ClientDTO client, String clientTerms, double amount, Quest quest) {
        this.manager = manager;
        this.client = client;
        this.clientTerms = clientTerms;
        this.amount = amount;
        this.quest = quest;
    }

    public ManagerDTO getManager() {
        return manager;
    }

    public void setManager(ManagerDTO manager) {
        this.manager = manager;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public String getClientTerms() {
        return clientTerms;
    }

    public void setClientTerms(String clientTerms) {
        this.clientTerms = clientTerms;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }
}
