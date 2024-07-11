package com.immortalidiot.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "deal")
public class Deal extends IdEntity {
    private Manager manager;
    private Client client;
    private String clientTerms;
    private int amount;
    private Quest quest;

    public Deal(Manager manager, Client client, String clientTerms, int amount) {
        this.manager = manager;
        this.client = client;
        this.clientTerms = clientTerms;
        this.amount = amount;
    }

    protected Deal() {}

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
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        if (amount > 0) { this.amount = amount; }
        else throw new IllegalArgumentException("Incorrect amount");
    }

    @OneToOne(mappedBy = "deal", targetEntity = Quest.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }
}

