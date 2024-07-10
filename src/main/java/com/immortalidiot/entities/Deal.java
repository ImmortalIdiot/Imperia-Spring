package com.immortalidiot.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "deal")
public class Deal {
    private String dealNumber;
    private Manager manager;
    private Client client;
    private String clientTerms;
    private double amount;
    private Quest quest; // TODO: implement Quest class

    public Deal() {}

    public Deal(String dealNumber, Manager manager, Client client, String clientTerms, double amount) {
        this.dealNumber = dealNumber;
        this.manager = manager;
        this.client = client;
        this.clientTerms = clientTerms;
        this.amount = amount;
    }

    @Id
    @Column(name = "deal_number", nullable = false)
    public String getDealNumber() {
        return dealNumber;
    }

    public void setDealNumber(String dealNumber) {
        this.dealNumber = dealNumber;
    }

    @ManyToOne
    @JoinColumn(name = "manager_code", nullable = false)
    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @ManyToOne
    @JoinColumn(name = "client_contact", nullable = false)
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Column(name = "client_terms", nullable = false)
    public String getClientTerms() {
        return clientTerms;
    }

    public void setClientTerms(String clientTerms) {
        this.clientTerms = clientTerms;
    }

    @Column(name = "amount", nullable = false)
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @OneToOne(mappedBy = "deal")
    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }
}

